package br.com.notification.service;

import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.controller.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    NotificationResponse createNotification(NotificationRequest request);

    NotificationResponse getNotification(Long notificationId);

    List<NotificationResponse> listAll();

    NotificationResponse updateNotification(Long notificationId, NotificationRequest request);

    void deleteNotification(Long notificationId);
}
