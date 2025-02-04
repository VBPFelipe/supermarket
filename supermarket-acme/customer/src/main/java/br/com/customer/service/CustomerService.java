package br.com.customer.service;

import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CustomerService {
//    CustomerResponse createCustomer(CustomerRequest customerRequest);
    String createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> listAll();

    CustomerResponse getCustomerById(Long customerId);

    CustomerResponse getCustomerByCpf(String customerCpf);

    CustomerResponse updateCustomerById(Long customerId, CustomerRequest request);

    void deleteCustomer(Long customerId);
}
