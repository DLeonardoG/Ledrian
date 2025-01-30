
package com.campus.ledrian.follow.domain;

import java.time.LocalDateTime;


public class FollowDTO {
    private Long id;
    private String usernameFollowed;
    private String usernameFollowing;
    private LocalDateTime date;

    public FollowDTO() {
    }

    public FollowDTO(Long id, String usernameFollowed, String usernameFollowing, LocalDateTime date) {
        this.id = id;
        this.usernameFollowed = usernameFollowed;
        this.usernameFollowing = usernameFollowing;
        this.date = date;
    }

    public FollowDTO(String usernameFollowed, String usernameFollowing, LocalDateTime date) {
        this.usernameFollowed = usernameFollowed;
        this.usernameFollowing = usernameFollowing;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameFollowed() {
        return usernameFollowed;
    }

    public void setUsernameFollowed(String usernameFollowed) {
        this.usernameFollowed = usernameFollowed;
    }

    public String getUsernameFollowing() {
        return usernameFollowing;
    }

    public void setUsernameFollowing(String usernameFollowing) {
        this.usernameFollowing = usernameFollowing;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
}
