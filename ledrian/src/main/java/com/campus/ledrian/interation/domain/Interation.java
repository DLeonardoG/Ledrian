package com.campus.ledrian.interation.domain;

import com.campus.ledrian.publication.domain.Publication;
import com.campus.ledrian.typeinteration.domain.TypeInteration;
import com.campus.ledrian.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Interation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
    
    @ManyToOne
    @JoinColumn(name = "user_receiving_id")
    private User userReceivingInteration;
    
    @ManyToOne
    @JoinColumn(name = "user_giving_id")
    private User userGivingInteration;

    @ManyToOne
    @JoinColumn(name = "type_interation_id")
    private TypeInteration typeInteration;

    private LocalDateTime date;

    private String comment;

    private String username;

    public Interation() {
    }

    public Interation(Long id, Publication publication, User userReceivingInteration, User userGivingInteration, TypeInteration typeInteration, LocalDateTime date, String comment, String username) {
        this.id = id;
        this.publication = publication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.typeInteration = typeInteration;
        this.date = date;
        this.comment = comment;
        this.username = username;
    }

    public Interation(Long id, Publication publication, User userReceivingInteration, User userGivingInteration, TypeInteration typeInteration, LocalDateTime date) {
        this.id = id;
        this.publication = publication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.typeInteration = typeInteration;
        this.date = date;
    }

    public Interation(Long id, Publication publication, User userReceivingInteration, User userGivingInteration, TypeInteration typeInteration, LocalDateTime date, String comment) {
        this.id = id;
        this.publication = publication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.typeInteration = typeInteration;
        this.date = date;
        this.comment = comment;
    }

    public Interation(Publication publication, User userReceivingInteration, User userGivingInteration, TypeInteration typeInteration, LocalDateTime date) {
        this.publication = publication;
        this.userReceivingInteration = userReceivingInteration;
        this.userGivingInteration = userGivingInteration;
        this.typeInteration = typeInteration;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public User getUserReceivingInteration() {
        return userReceivingInteration;
    }

    public void setUserReceivingInteration(User userReceivingInteration) {
        this.userReceivingInteration = userReceivingInteration;
    }

    public User getUserGivingInteration() {
        return userGivingInteration;
    }

    public void setUserGivingInteration(User userGivingInteration) {
        this.userGivingInteration = userGivingInteration;
    }

    public TypeInteration getTypeInteration() {
        return typeInteration;
    }

    public void setTypeInteration(TypeInteration typeInteration) {
        this.typeInteration = typeInteration;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
