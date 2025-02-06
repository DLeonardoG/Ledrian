package com.campus.ledrian.interation.infrastructure;

import com.campus.ledrian.interation.domain.NotificationDTO;
import com.campus.ledrian.interation.service.InterationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private InterationServiceImpl notificationService;

    // Endpoint para obtener notificaciones no vistas
    @GetMapping("/unseen/{userId}")
    public ResponseEntity<List<NotificationDTO>> getUnseenNotifications(@PathVariable Long userId) {
        List<NotificationDTO> notifications = notificationService.getUnseenNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    // Endpoint para marcar una notificación como vista
    @PutMapping("/mark-as-seen/{notificationId}")
    public ResponseEntity<Void> markNotificationAsSeen(@PathVariable Long notificationId) {
        notificationService.markNotificationAsSeen(notificationId);
        return ResponseEntity.ok().build();
    }
}
