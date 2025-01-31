package com.campus.ledrian.user.domain;

import com.campus.ledrian.interation.domain.Interation;
import com.campus.ledrian.publication.domain.Publication;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String photo;
    private String lastname;
    private String bio;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publication> publications;

    // Relación de seguidores (followers)
    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> followers;

    // Relación de seguidos (following)
    @ManyToMany(mappedBy = "followers")
    private List<User> following;

    @OneToMany(mappedBy = "userReceivingInteration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interation> received;

    @OneToMany(mappedBy = "userGivingInteration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interation> delivered;

    // Constructores, getters y setters
    public User() {
    }

    public User(Long id, String name, String email, String username, String password, String photo, String lastname, String bio, List<Publication> publications, List<User> followers, List<User> following, List<Interation> received, List<Interation> delivered) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.lastname = lastname;
        this.bio = bio;
        this.publications = publications;
        this.followers = followers;
        this.following = following;
        this.received = received;
        this.delivered = delivered;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Interation> getReceived() {
        return received;
    }

    public void setReceived(List<Interation> received) {
        this.received = received;
    }

    public List<Interation> getDelivered() {
        return delivered;
    }

    public void setDelivered(List<Interation> delivered) {
        this.delivered = delivered;
    }
}