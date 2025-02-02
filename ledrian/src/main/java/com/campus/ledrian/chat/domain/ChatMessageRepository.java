
package com.campus.ledrian.chat.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface ChatMessageRepository {
     List<ChatMessage> findAll();

    ChatMessage save(ChatMessage chat);

    Optional<ChatMessage> findById(Long id);
    
     List<ChatMessage> findChatMessagesBetweenUsers(@Param("senderId") Long senderId, @Param("recipientId") Long recipientId);
    
    
   
}
