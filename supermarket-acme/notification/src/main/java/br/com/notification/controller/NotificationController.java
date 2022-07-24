package br.com.notification.controller;

import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.controller.response.NotificationResponse;
import br.com.notification.service.NotificationService;
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
@RequestMapping("/api/v1/notification")
@Tag(name = "Notification API", description = "Notification creation and management")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "Create Notification", description = "Create Notification to fraud system")
    @ApiResponse(responseCode = "201", description = "A Notification was created successfully")
    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest request) {
        request.setSendAt(LocalDateTime.now());
        log.info("calling controller to create notification {}", request );
        this.notificationService.createNotification(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Find a Notification", description = "Find a Notification by ID")
    @ApiResponse(responseCode = "200", description = "A Notification was found successfully")
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public NotificationResponse getNotification(Long notificationId) {
        log.info("calling controller to get notification");
        return this.notificationService.getNotification(notificationId);
    }

    @Operation(summary = "Find All Notifications", description = "Find all Notifications")
    @ApiResponse(responseCode = "200", description = "find all notification")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationResponse> listAllNotifications() {
        log.info("calling controller to list all notifications {}");
        return this.notificationService.listAll();
    }

    @Operation(summary = "Updates Notification", description = "Upate a Notification by ID")
    @ApiResponse(responseCode = "200", description = "A Notification was updated successfully")
    @PutMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public NotificationResponse updateNotification(@PathVariable("id") Long notificationId, @RequestBody NotificationRequest request) {
        log.info("calling controller to update a notification");
        return this.notificationService.updateNotification(notificationId, request);
    }

    @Operation(summary = "remove notification by id", description = "delete a notification by id")
    @ApiResponse(responseCode = "204", description = "delete success")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteNotification(@RequestParam Long id) {
        log.info("calling controller to remove notification by id{}");
        this.notificationService.deleteNotification(id);
    }
}