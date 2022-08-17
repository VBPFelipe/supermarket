package br.com.fraud.service;

import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;

import java.util.List;

public interface FraudService {

    FraudResponse registeredFraud(FraudRequest fraudRequest);

    Boolean isFraud(String customerCpf);

    FraudResponse getFraudByCustomerCpf(String customerCpf);

    FraudResponse getFraudById(Long fraudId);

    List<FraudResponse> listAll();

    FraudResponse updateFraud(Long fraudId, FraudRequest request);

    void deleteFraud(Long fraudId);
}
