package br.com.customer.service;

import br.com.client.fraud.response.ClientFraudService;
import br.com.client.notification.request.NotificationPayload;
import br.com.customer.config.Constants;
import br.com.customer.config.ConvertUtils;
import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import br.com.customer.model.CustomerEntity;
import br.com.customer.repository.CustomerRepository;
import br.com.rabbitmq.RabbitMQMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    private final ConvertUtils convertUtils;

    private ClientFraudService clientFraudService;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ConvertUtils convertUtils,
                               ClientFraudService clientFraudService,
                               RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.customerRepository = customerRepository;
        this.convertUtils = convertUtils;
        this.clientFraudService = clientFraudService;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }

    @Override
    public String createCustomer(CustomerRequest customerRequest) {
        if(customerRequest == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        var internalResponseFraud = this.clientFraudService.getFraud(customerRequest.getCpf());

        if( this.clientFraudService.isFraud(customerRequest.getCpf()) ) {

            logger.trace("Calling service CPF: {}", customerRequest.getCpf());
            // send message to queue
            var notificationPayload = NotificationPayload
                    .builder()
                    .customer_email(customerRequest.getEmail())
                    .sender(customerRequest.getName())
                    .customer_cpf(customerRequest.getCpf())
                    .message("Customer " + customerRequest.getName() + " with cpf " + internalResponseFraud.getCustomerCpf() + " " + internalResponseFraud.getDescription())
                    .build();

            this.rabbitMQMessageProducer.publish(
                    notificationPayload,
                    Constants.EXCHANGE_TO_NOTIFICATION,
                    Constants.ROUTING_KEY
            );

            logger.trace("The customer is a fraud, CPF {}", customerRequest.getCpf());
            return ("The customer is a fraud, cpf :" + customerRequest.getCpf());

        } else {
            logger.trace("Calling the method to create customer {}", customerRequest);

            var customerEntity =
                    (CustomerEntity) this.convertUtils.convertRequestToEntity(customerRequest, CustomerEntity.class);

            var entity = this.customerRepository.save(customerEntity);
            logger.trace("calling fraud service to customerId {}", entity.getCpf());

            this.convertUtils.convertEntityToResponse(entity, CustomerResponse.class);

            return "Customer created with success";
        }

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
