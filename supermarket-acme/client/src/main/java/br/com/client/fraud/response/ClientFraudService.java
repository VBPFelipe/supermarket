package br.com.client.fraud.response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "fraud",
        url = "${client.fraud.url}"
)
public interface ClientFraudService {

    @GetMapping(value = "/is-fraud/{cpf}", consumes = "application/json")
    Boolean isFraud(@PathVariable("cpf") String cpf);

    @GetMapping(value = "/find-fraud/{cpf}", consumes = "application/json")
    InternalResponseFraud getFraud(@PathVariable("cpf") String cpf);
}
