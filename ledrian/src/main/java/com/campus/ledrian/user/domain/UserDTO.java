
package com.campus.ledrian.user.domain;

import java.util.List;

public class UserDTO {
    
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String photo;
    private String lastname;
    private String bio;
    private List<Long> publications;
    private List<Long> followers;
    private List<Long> following;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String username, String password, String photo, String lastname, String bio, List<Long> publications, List<Long> followers, List<Long> following) {
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
    }

    public UserDTO(String name, String email, String username, String password, String photo, String lastname, String bio, List<Long> publications, List<Long> followers, List<Long> following) {
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
    }
    
    

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

    public List<Long> getPublications() {
        return publications;
    }

    public void setPublications(List<Long> publications) {
        this.publications = publications;
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

    public List<Long> getFollowing() {
        return following;
    }

    public void setFollowing(List<Long> following) {
        this.following = following;
    }
    
    
    
}
