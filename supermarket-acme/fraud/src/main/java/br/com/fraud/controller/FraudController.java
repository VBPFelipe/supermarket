package br.com.fraud.controller;

import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.service.FraudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/frauds")
@Tag(name = "Api to fraud management", description = "Fraud creation and management")
public class FraudController {

    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @Operation(summary = "Create fraud", description = "Creates a fraud")
    @ApiResponse(responseCode = "201", description = "Fraud was successfully created")
    @PostMapping
    public ResponseEntity<FraudResponse> fraudRegistry(@RequestBody FraudRequest fraudRequest) {
        fraudRequest.setCreatedAt(LocalDateTime.now());
        fraudRequest.setDescription(fraudRequest.getIsFraud() ? "It's a fraud" : "It's not a fraud" );
        log.info("calling controller to create fraud {}", fraudRequest );
        this.fraudService.registeredFraud(fraudRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Customer Is Fraud?", description = "Finds if a customer is fraud by customer's cpf")
    @ApiResponse(responseCode = "200", description = "Information about if a customer is fraud was got successfully")
    @GetMapping("/is-fraud/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isFraud(@PathVariable("cpf") String customerCpf) {
        log.info("Calling controler to get if a customer is fraud");
        return this.fraudService.isFraud(customerCpf);
    }

    @Operation(summary = "Get fraud", description = "Find a fraud Id")
    @ApiResponse(responseCode = "200", description = "A fraud was got by id successfully")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FraudResponse getFraud(@PathVariable("id") Long fraudId) {
        log.info("Calling controler to get a registred fraud");
        return this.fraudService.getFraudById(fraudId);
    }

    @Operation(summary = "List all frauds", description = "Find all frauds")
    @ApiResponse(responseCode = "200", description = "all frauds were gotten successfully")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FraudResponse> listAllFrauds() {
        log.info("Calling controler to get all registred fraud");
        return this.fraudService.listAll();
    }

    @Operation(summary = "Update a fraud", description = "Updates a fraud by id")
    @ApiResponse(responseCode = "200", description = "Fraud was updated successfully")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FraudResponse> updateFraud(@PathVariable("id") Long fraudId, @RequestBody FraudRequest request) {
        log.info("calling controller to update fraud {}", request );
        request.setDescription(request.getIsFraud() ? "It's a fraud" : "It's not a fraud" );
        this.fraudService.updateFraud(fraudId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete Fraud", description = "Delete a fraud by id")
    @ApiResponse(responseCode = "200", description = "Fraud was deleted successfully")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFraud(@PathVariable("id") Long fraudId) {
        log.info("Calling controler to delete a fraud");
        this.fraudService.deleteFraud(fraudId);
    }
}
