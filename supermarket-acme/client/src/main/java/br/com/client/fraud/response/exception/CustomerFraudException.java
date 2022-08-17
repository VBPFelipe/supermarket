package br.com.client.fraud.response.exception;

public class CustomerFraudException extends RuntimeException {

    public CustomerFraudException(String msg) {
        super(msg);
    }
}