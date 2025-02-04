package br.com.notification.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {

    private Long id;
    private LocalDateTime sendAt;
    private String cpfCustomer;
    private String message;
    private String sender;
    private String customer_email;
}
