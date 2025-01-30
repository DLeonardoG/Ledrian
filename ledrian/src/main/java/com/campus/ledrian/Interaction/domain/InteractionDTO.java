package com.campus.ledrian.interaction.domain;

import java.time.LocalDateTime;

public class InteractionDTO {

    private Long id;
    private Long idPublication;
    private String userReceivingInteraction;
    private String userGivingInteraction;
    private String type;
    private LocalDateTime date;

    public InteractionDTO() {
    }

    public InteractionDTO(Long id, Long idPublication, String userReceivingInteraction, String userGivingInteraction, String type, LocalDateTime date) {
        this.id = id;
        this.idPublication = idPublication;
        this.userReceivingInteraction = userReceivingInteraction;
        this.userGivingInteraction = userGivingInteraction;
        this.type = type;
        this.date = date;
    }

    public InteractionDTO(Long idPublication, String userReceivingInteraction, String userGivingInteraction, String type, LocalDateTime date) {
        this.idPublication = idPublication;
        this.userReceivingInteraction = userReceivingInteraction;
        this.userGivingInteraction = userGivingInteraction;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(Long idPublication) {
        this.idPublication = idPublication;
    }

    public String getUserReceivingInteraction() {
        return userReceivingInteraction;
    }

    public void setUserReceivingInteraction(String userReceivingInteraction) {
        this.userReceivingInteraction = userReceivingInteraction;
    }

    public String getUserGivingInteraction() {
        return userGivingInteraction;
    }

    public void setUserGivingInteraction(String userGivingInteraction) {
        this.userGivingInteraction = userGivingInteraction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
