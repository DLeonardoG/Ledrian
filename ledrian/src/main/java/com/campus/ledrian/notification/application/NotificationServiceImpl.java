package com.campus.ledrian.notification.application;

import com.campus.ledrian.notification.domain.NotificationDTO;
import com.campus.ledrian.notification.domain.Notification;
import com.campus.ledrian.notification.domain.NotificationRepository;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }


    public List<NotificationDTO> findNotificationsByUser(Long id) {
        return notificationRepository.findNotificationsByUserId(id).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markAllNotificationsAsRead(Long userId) {
        notificationRepository.markAllNotificationsAsRead(userId);
    }

    public Optional<NotificationDTO> findById(Long id) {
        return notificationRepository.findById(id)
                .map(this::toDTO);
    }

    public NotificationDTO createNotification(NotificationDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de de notification es nulo");
        }
        Notification notification = toEntity(dto);
        notification.setDate(LocalDateTime.now());

        Notification savedNotification = notificationRepository.save(notification);
        return toDTO(savedNotification);
    }

    public NotificationDTO toDTO(Notification notification) {
        if (notification == null) {
            return null;
        }
        return new NotificationDTO(
                notification.getId(),
                notification.getGiver() != null ? notification.getGiver().getId() : null,
                notification.getReceiver() != null ? notification.getReceiver().getId() : null,
                notification.getDate(),
                notification.getContent(),
                notification.getType(),
                notification.isChecked()
        );
    }

    public Notification toEntity(NotificationDTO dto) {
        if (dto == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setId(dto.getId());

        User giver = userRepository.findById(dto.getIdGiver())
                .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
        notification.setGiver(giver);
        User receiver = userRepository.findById(dto.getIdReceiver())
                .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));
        notification.setReceiver(receiver);

        notification.setDate(dto.getDate());
        notification.setContent(dto.getContent());
        notification.setType(dto.getType());
        notification.setChecked(dto.isChecked());
        return notification;
    }

}
