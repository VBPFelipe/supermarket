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

    @Operation(summary = "create notification", description = "create Notification to fraud system")
    @ApiResponse(responseCode = "201", description = "Notification success created")
    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest request) {
        log.info("calling controller to create notification {}", request );
        this.notificationService.createNotification(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "find all notifications", description = "list all notifications")
    @ApiResponse(responseCode = "200", description = "find all notification")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationResponse> listAllNotifications() {
        log.info("calling controller to list all notifications {}");
        return this.notificationService.listAll();
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
