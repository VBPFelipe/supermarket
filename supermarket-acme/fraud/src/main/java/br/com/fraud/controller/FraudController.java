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

    @Operation(summary = "Create fraud", description = "Create fraud")
    @ApiResponse(responseCode = "201", description = "Fraud successfully created")
    @PostMapping
    public ResponseEntity<FraudResponse> fraudRegistry(@RequestBody FraudRequest fraudRequest) {
        log.info("calling controller to create fraud {}", fraudRequest );
        this.fraudService.registeredFraud(fraudRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Is Fraud?", description = "Get if it's fraud")
    @ApiResponse(responseCode = "200", description = "Got Fraud successfully")
    @GetMapping("/is-fraud/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isFraud(@PathVariable("customerId") Long customerId) {
        return this.fraudService.isFraud(customerId);
    }

    @Operation(summary = "List all frauds", description = "Find all frauds")
    @ApiResponse(responseCode = "200", description = "get all frauds")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FraudResponse> listAllFrauds() {
        return this.fraudService.listAll();
    }
}
