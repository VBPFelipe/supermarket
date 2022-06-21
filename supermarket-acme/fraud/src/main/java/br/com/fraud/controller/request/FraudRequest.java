package br.com.fraud.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FraudRequest {

    private String description;
    private Long customerId;
    private boolean isFraud;
    private LocalDateTime createdAt;
}
