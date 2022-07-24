package br.com.fraud.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FraudResponse {

    private Long id;
    private String description;
    private String customerCpf;
    private Boolean isFraud;
    private LocalDateTime createdAt;
}
