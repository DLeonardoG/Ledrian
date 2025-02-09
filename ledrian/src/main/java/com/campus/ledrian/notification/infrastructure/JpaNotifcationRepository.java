package com.campus.ledrian.notification.infrastructure;

import com.campus.ledrian.notification.domain.Notification;
import com.campus.ledrian.notification.domain.Notification;
import com.campus.ledrian.notification.domain.NotificationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaNotifcationRepository  extends JpaRepository<Notification, Long>, NotificationRepository {

    @Query("SELECT n FROM Notification n WHERE n.receiver.id = :userId")
    List<Notification> findNotificationsByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE Notification n SET n.checked = true WHERE n.receiver.id = :userId")
    void markAllNotificationsAsRead(@Param("userId") Long userId);

}
