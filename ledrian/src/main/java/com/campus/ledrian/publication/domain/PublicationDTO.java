
package com.campus.ledrian.publication.domain;

import java.time.LocalDateTime;


public class PublicationDTO {
    private Long id;
    private String description;
    private String photo;
    private String username;
    private LocalDateTime date;
    private Long publisherId;

    public PublicationDTO(Long id, String description, String photo, String username, LocalDateTime date, Long publisherId) {
        this.id = id;
        this.description = description;
        this.photo = photo;
        this.username = username;
        this.date = date;
        this.publisherId = publisherId;
    }

    public PublicationDTO() {
    }

    public PublicationDTO(String description, String photo, String username, LocalDateTime date) {
        this.description = description;
        this.photo = photo;
        this.username = username;
        this.date = date;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
}
