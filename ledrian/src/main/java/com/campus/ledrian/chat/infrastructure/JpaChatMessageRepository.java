
package com.campus.ledrian.chat.infrastructure;

import com.campus.ledrian.chat.domain.ChatMessage;
import com.campus.ledrian.chat.domain.ChatMessageRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface JpaChatMessageRepository extends JpaRepository<ChatMessage, Long>, ChatMessageRepository {
    
    @Query("SELECT m FROM ChatMessage m WHERE " +
           "(m.sender.id = :userId1 AND m.recipient.id = :userId2) OR " +
           "(m.sender.id = :userId2 AND m.recipient.id = :userId1) " +
           "ORDER BY m.timestamp ASC")
        
        List<ChatMessage> findChatMessagesBetweenUsers(@Param("userId1") Long userId1,
                                                   @Param("userId2") Long userId2);
}
