package br.com.customer.service;

import br.com.customer.config.ConvertUtils;
import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import br.com.customer.model.CustomerEntity;
import br.com.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ConvertUtils convertUtils;

    public CustomerServiceImpl(CustomerRepository customerRepository, ConvertUtils convertUtils) {
        this.customerRepository = customerRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        if(customerRequest == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        var customerEntity =
                (CustomerEntity) this.convertUtils.convertRequestToEntity(customerRequest, CustomerEntity.class);

        var entity = this.customerRepository.save(customerEntity);
        return (CustomerResponse) this.convertUtils.convertEntityToResponse(entity, CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> listAll() {
        List<CustomerEntity> listEntity = this.customerRepository.findAll();
        List<CustomerResponse> listResponse = (List<CustomerResponse>) this.convertUtils.convertToListResponse(listEntity, CustomerResponse.class);
        return listResponse;
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {
        if(customerId == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        CustomerResponse response = (CustomerResponse) this.convertUtils.convertEntityToResponse(
                                                            this.customerRepository.findById(customerId)
                                                                    .orElse(null),
                                                            CustomerResponse.class
                                                        );

        return response;
    }

    @Override
    public CustomerResponse getCustomerByCpf(String customerCpf) {
        if(customerCpf == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        var entity = (CustomerEntity) this.customerRepository.findByCpf(customerCpf);
        CustomerResponse response = (CustomerResponse) this.convertUtils.convertEntityToResponse(entity, CustomerResponse.class);

        return response;
    }

    @Override
    public CustomerResponse updateCustomerById(Long customerId, CustomerRequest request) {
        if(request == null || customerId == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        CustomerEntity entity = (CustomerEntity) this.convertUtils.convertRequestToEntity(request, CustomerEntity.class);
        entity.setId(customerId);

        return (CustomerResponse) this.convertUtils.convertEntityToResponse(this.customerRepository.save(entity), CustomerResponse.class);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        if(customerId == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        this.customerRepository.deleteById(customerId);
    }
}
