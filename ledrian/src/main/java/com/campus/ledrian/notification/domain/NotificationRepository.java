package com.campus.ledrian.notification.domain;

import com.campus.ledrian.notification.domain.Notification;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    List<Notification> findUnreadNotificationsByUserId(Long userId);
    List<Notification> findReadNotificationsByUserId( Long userId);
    void markNotificationAsRead(Long notificationId);
    Notification save(Notification notification);
    List<Notification> findAll();
    Optional<Notification> findById(Long id);



}
