
package com.campus.ledrian.chat.service;

import com.campus.ledrian.chat.domain.ChatMessage;
import com.campus.ledrian.chat.domain.ChatMessageDTO;
import com.campus.ledrian.chat.domain.ChatMessageRepository;
import com.campus.ledrian.notification.application.NotificationServiceImpl;
import com.campus.ledrian.notification.domain.NotificationDTO;
import com.campus.ledrian.user.domain.User;
import com.campus.ledrian.user.domain.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageServiceImpl {
    
     @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
     private NotificationServiceImpl notificationService;
    @Autowired
    private UserRepository usuarioRepository;

    public List<ChatMessageDTO> getChatMessagesBetweenUsers(Long userId1, Long userId2) {
        List<ChatMessage> chatMessages = chatMessageRepository.findChatMessagesBetweenUsers(userId1, userId2);
        return chatMessages.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ChatMessageDTO convertToDTO(ChatMessage chatMessage) {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setId(chatMessage.getId());
        dto.setContent(chatMessage.getContent());
        dto.setTimestamp(chatMessage.getTimestamp());
        dto.setSenderId(chatMessage.getSender().getId());
        dto.setRecipientId(chatMessage.getRecipient().getId());
        return dto;
    }

    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessageDTO) {
        User sender = usuarioRepository.findById(chatMessageDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User recipient = usuarioRepository.findById(chatMessageDTO.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(chatMessageDTO.getContent());
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessage.setSender(sender);
        chatMessage.setRecipient(recipient);

        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setType("Message");
        notificationDTO.setContent(chatMessage.getContent());
        notificationDTO.setIdGiver(chatMessage.getSender().getId());
        notificationDTO.setIdReceiver(chatMessage.getRecipient().getId());
        notificationService.createNotification(notificationDTO);

        return convertToDTO(savedMessage);
    }
}
