package br.com.fraud.service;

import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;

import java.util.List;

public interface FraudService {

    boolean isFraud(Long customerId);
    FraudResponse registeredFraud(FraudRequest fraudRequest);
    List<FraudResponse> listAll();
}
