package br.com.client.notification.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPayload {

    private String sender;
    private String customer_email;
    private String customer_cpf;
    private String message;
}
