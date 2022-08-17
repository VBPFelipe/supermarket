package br.com.fraud.service;

import br.com.fraud.config.ConvertUtils;
import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.model.FraudEntity;
import br.com.fraud.repository.FraudRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class FraudServiceImpl implements FraudService {

    private final FraudRepository fraudRepository;

    private final ConvertUtils convertUtils;

    private Logger logger = LogManager.getLogger(FraudResponse.class);

    public FraudServiceImpl(FraudRepository fraudRepository, ConvertUtils convertUtils) {
        this.fraudRepository = fraudRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public FraudResponse registeredFraud(FraudRequest fraudRequest) {
        if(fraudRequest == null ){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        var entity = (FraudEntity) this.convertUtils.convertRequestToEntity(fraudRequest, FraudEntity.class);
        return (FraudResponse) this.convertUtils.convertEntityToResponse(this.fraudRepository.save(entity) , FraudResponse.class);
    }

    @Override
    public Boolean isFraud(String customerCpf) {
        if(customerCpf == null ){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        var entity = (FraudEntity) this.fraudRepository.getFraudEntityByCustomerCpf(customerCpf);
        return entity != null ? entity.getIsFraud() : Boolean.FALSE;
    }

    @Override
    public FraudResponse getFraudByCustomerCpf(String customerCpf) {
        if(customerCpf == null ){ throw new HttpClientErrorException(HttpStatus.NOT_FOUND); }
        var entity = (FraudEntity) this.fraudRepository.getFraudEntityByCustomerCpf(customerCpf);
        if (entity == null) {
            logger.trace("Document not found {}", customerCpf);
            return (FraudResponse) this.convertUtils.convertEntityToResponse(ResponseEntity.noContent().build(), FraudResponse.class);
        } else {
            return (FraudResponse) this.convertUtils.convertEntityToResponse(entity, FraudResponse.class);
        }
    }

    @Override
    public FraudResponse getFraudById(Long fraudId) {
        if(fraudId == null ){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return (FraudResponse) this.convertUtils.convertEntityToResponse(this.fraudRepository.getById(fraudId), FraudResponse.class);
    }

    @Override
    public List<FraudResponse> listAll() {
        List<FraudEntity> entities = this.fraudRepository.findAll();
        return (List<FraudResponse>) this.convertUtils.convertToListResponse(entities, FraudResponse.class);
    }

    @Override
    public FraudResponse updateFraud(Long fraudId, FraudRequest request) {
        if(request == null || fraudId == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        var entity = (FraudEntity) this.convertUtils.convertRequestToEntity(request, FraudEntity.class);
        entity.setId(fraudId);
        return (FraudResponse) this.convertUtils.convertEntityToResponse(this.fraudRepository.save(entity) , FraudResponse.class);
    }

    @Override
    public void deleteFraud(Long fraudId) {
        if( fraudId == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        this.fraudRepository.deleteById(fraudId);
    }
}
