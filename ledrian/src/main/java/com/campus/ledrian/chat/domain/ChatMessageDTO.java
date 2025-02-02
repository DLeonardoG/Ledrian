
package com.campus.ledrian.chat.domain;

import java.time.LocalDateTime;

public class ChatMessageDTO {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private Long senderId;
    private Long recipientId;

    public ChatMessageDTO(Long id, String content, LocalDateTime timestamp, Long senderId, Long recipientId) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public ChatMessageDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    
}
