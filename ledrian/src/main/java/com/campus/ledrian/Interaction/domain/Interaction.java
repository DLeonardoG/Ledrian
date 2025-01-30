package com.campus.ledrian.interaction.domain;

import com.campus.ledrian.publication.domain.Publication;
import com.campus.ledrian.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
    
    @ManyToOne
    @JoinColumn(name = "user_receiving_id")
    private User userReceivingInteraction;
    
    @ManyToOne
    @JoinColumn(name = "user_giving_id")
    private User userGivingInteraction;

    @ManyToOne
    @JoinColumn(name = "type_interaction_id")
    private Interaction typeInteration;

    private LocalDateTime date;

    public Interaction() {
    }

    public Interaction(Long id, Publication publication, User userReceivingInteraction, User userGivingInteraction, Interaction typeInteration, LocalDateTime date) {
        this.id = id;
        this.publication = publication;
        this.userReceivingInteraction = userReceivingInteraction;
        this.userGivingInteraction = userGivingInteraction;
        this.typeInteration = typeInteration;
        this.date = date;
    }

    public Interaction(Publication publication, User userReceivingInteraction, User userGivingInteraction, Interaction typeInteration, LocalDateTime date) {
        this.publication = publication;
        this.userReceivingInteraction = userReceivingInteraction;
        this.userGivingInteraction = userGivingInteraction;
        this.typeInteration = typeInteration;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public User getUserReceivingInteraction() {
        return userReceivingInteraction;
    }

    public void setUserReceivingInteraction(User userReceivingInteraction) {
        this.userReceivingInteraction = userReceivingInteraction;
    }

    public User getUserGivingInteraction() {
        return userGivingInteraction;
    }

    public void setUserGivingInteraction(User userGivingInteraction) {
        this.userGivingInteraction = userGivingInteraction;
    }

    public Interaction getTypeInteraction() {
        return typeInteration;
    }

    public void setTypeInteraction(Interaction typeInteration) {
        this.typeInteration = typeInteration;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    
}
