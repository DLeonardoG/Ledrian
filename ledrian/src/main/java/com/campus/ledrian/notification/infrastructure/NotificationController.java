package com.campus.ledrian.notification.infrastructure;

import com.campus.ledrian.notification.application.NotificationServiceImpl;
import com.campus.ledrian.notification.domain.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    // Obtener notificaciones de un usuario
    @GetMapping("/all/{userId}")
    public List<NotificationDTO> getNotifications(@PathVariable Long userId) {
        return notificationService.findNotificationsByUser(userId);
    }

    // Marcar todas las notificaciones de un usuario como leídas
        @PutMapping("/read-all/{userId}")
    public ResponseEntity<Void> markAllNotificationsAsRead(@PathVariable Long userId) {
        notificationService.markAllNotificationsAsRead(userId);
        return ResponseEntity.noContent().build();
    }

    // Obtener una notificación por su ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        return notificationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva notificación
    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO dto) {
        NotificationDTO createdNotification = notificationService.createNotification(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }
}
