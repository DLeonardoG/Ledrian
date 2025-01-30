package com.campus.ledrian.interation.domain;

import java.time.LocalDateTime;

public class InterationDTO {

    private Long id;
    private Long idPublication;
    private String userReceivingInteration;
    private String userGivingInteration;
    private String type;
    private LocalDateTime date;

    public InterationDTO() {
    }

    public InterationDTO(Long id, Long idPublication, String userReceivingInteration, String userGivingInteration, String type, LocalDateTime date) {
        this.id = id;
        this.idPublication = idPublication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.type = type;
        this.date = date;
    }

    public InterationDTO(Long idPublication, String userReceivingInteration, String userGivingInteration, String type, LocalDateTime date) {
        this.idPublication = idPublication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
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

    public String getUserReceivingInteration() {
        return userReceivingInteration;
    }

    public void setUserReceivingInteration(String userReceivingInteration) {
        this.userReceivingInteration = userReceivingInteration;
    }

    public String getUserGivingInteration() {
        return userGivingInteration;
    }

    public void setUserGivingInteration(String userGivingInteration) {
        this.userGivingInteration = userGivingInteration;
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
