package br.com.customer.controller;

import br.com.customer.controller.request.CustomerRequest;
import br.com.customer.controller.response.CustomerResponse;
import br.com.customer.model.CustomerEntity;
import br.com.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Api to customer management", description = "Customer creation and management")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "create customer", description = "create customer to fraud system")
    @ApiResponse(responseCode = "201", description = "Customer success created")
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("calling controller to create customer {}", customerRequest );
        this.customerService.createCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "find all customers", description = "find all customers to fraud system")
    @ApiResponse(responseCode = "200", description = "find all customers")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> listAllCustomer(){
        log.info("Calling controler to list all customers");
        return this.customerService.listAll();
    }

    @Operation(summary = "Get a customer", description = "Obtain a customer by id")
    @ApiResponse(responseCode = "200", description = "Find a customer by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomer(@PathVariable("id") Long customerId) {
        log.info("Calling controller to get a customer");
        return this.customerService.getCustomerById(customerId);
    }

    @Operation(summary = "Get a customer", description = "Obtain a customer by cpf")
    @ApiResponse(responseCode = "200", description = "Find a customer by cpf")
    @GetMapping("/get-customer/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomer(@PathVariable("cpf") String customerCpf) {
        log.info("Calling controller to get a customer");
        CustomerResponse response = this.customerService.getCustomerByCpf(customerCpf);
        return response;
    }

    @Operation(summary = "Update a customer", description = "Update a customer by id")
    @ApiResponse(responseCode = "200", description = "Update a customer information by id")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerRequest request) {
        log.info("calling controller to update a customer {}", request );
        this.customerService.updateCustomerById(customerId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete a customer", description = "Delete a customer by id")
    @ApiResponse(responseCode = "200", description = "Delete a customer by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") Long customerId) {
        log.info("calling controller to delete a customer {}", customerId);
        this.customerService.deleteCustomer(customerId);
    }
}
