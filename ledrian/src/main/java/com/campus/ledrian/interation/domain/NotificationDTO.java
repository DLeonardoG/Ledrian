package com.campus.ledrian.interation.domain;

import java.time.LocalDateTime;

public class NotificationDTO {
    private Long id;
    private Long receiver; // ID del usuario receptor
    private Long giver;    // ID del usuario que realiza la interacción
    private Long type;     // ID del tipo de interacción
    private boolean check; // Estado de la notificación (vista o no vista)
    private String comment; // Comentario de la interacción
    private LocalDateTime date; // Fecha de la interacción
    private String username; // Nombre del usuario que realiza la interacción

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Long getGiver() {
        return giver;
    }

    public void setGiver(Long giver) {
        this.giver = giver;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}