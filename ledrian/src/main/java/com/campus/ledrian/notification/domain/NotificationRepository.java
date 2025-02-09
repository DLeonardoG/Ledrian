package com.campus.ledrian.notification.domain;

import com.campus.ledrian.notification.domain.Notification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    @Modifying
    @Query("UPDATE Notification n SET n.checked = true WHERE n.receiver.id = :userId")
    void markAllNotificationsAsRead(@Param("userId") Long userId);

    @Query("SELECT n FROM Notification n WHERE n.receiver.id = :userId")
    List<Notification> findNotificationsByUserId(@Param("userId") Long userId);

    Notification save(Notification notification);
    List<Notification> findAll();
    Optional<Notification> findById(Long id);



}
