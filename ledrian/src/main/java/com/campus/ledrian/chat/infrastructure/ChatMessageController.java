
package com.campus.ledrian.chat.infrastructure;
import com.campus.ledrian.chat.domain.ChatMessageDTO;
import com.campus.ledrian.chat.service.ChatMessageServiceImpl;
import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageServiceImpl chatMessageServiceImpl;

      @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessageDTO chatMessageDTO) {
        // Guardar el mensaje en la base de datos
        ChatMessageDTO savedMessage = chatMessageServiceImpl.sendMessage(chatMessageDTO);

        // Enviar el mensaje al destinatario
        String recipientDestination = "/user/" + chatMessageDTO.getRecipientId() + "/queue/messages";
        messagingTemplate.convertAndSend(recipientDestination, savedMessage);

        // Enviar el mensaje al remitente para actualizar su vista también
        String senderDestination = "/user/" + chatMessageDTO.getSenderId() + "/queue/messages";
        messagingTemplate.convertAndSend(senderDestination, savedMessage);
    }
    @GetMapping("/messages")
    public List<ChatMessageDTO> getMessagesBetweenUsers(
            @RequestParam Long senderId, 
            @RequestParam Long recipientId) {
        return chatMessageServiceImpl.getChatMessagesBetweenUsers(senderId, recipientId);
    }
    
}
