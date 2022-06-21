package br.com.fraud.service;

import br.com.fraud.config.ConvertUtils;
import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.model.FraudEntity;
import br.com.fraud.repository.FraudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FraudServiceImpl implements FraudService{

    private final FraudRepository fraudRepository;

    private final ConvertUtils convertUtils;

    public FraudServiceImpl(FraudRepository fraudRepository, ConvertUtils convertUtils) {
        this.fraudRepository = fraudRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public FraudResponse registeredFraud(FraudRequest fraudRequest) {
        var entity = (FraudEntity) this.convertUtils.convertRequestToEntity(fraudRequest, FraudEntity.class);
        return (FraudResponse) this.convertUtils.convertEntityToResponse(this.fraudRepository.save(entity) , FraudResponse.class);
    }

    @Override
    public boolean isFraud(Long customerId) {
        var fraud = this.fraudRepository.save(
                FraudEntity.builder()
                        .customerId(customerId)
                        .isFraud(false)
                        .description("This is not a fraud")
                        .createdAt(LocalDateTime.now())
                        .build());
        return fraud.isFraud();
    }

    @Override
    public List<FraudResponse> listAll() {
        List<FraudEntity> entities = this.fraudRepository.findAll();
        return (List<FraudResponse>) this.convertUtils.convertToListResponse(entities, FraudResponse.class);
    }
}
