
package com.campus.ledrian.follow.domain;

import java.time.LocalDateTime;


public class FollowDTO {
    private Long id;
    private Long usernameFollowedId;
    private Long usernameFollowingId;
    private LocalDateTime date;

    public FollowDTO() {
    }

    public FollowDTO(Long id, Long usernameFollowedId, Long usernameFollowingId, LocalDateTime date) {
        this.id = id;
        this.usernameFollowedId = usernameFollowedId;
        this.usernameFollowingId = usernameFollowingId;
        this.date = date;
    }

    public FollowDTO(Long usernameFollowedId, Long usernameFollowingId, LocalDateTime date) {
        this.usernameFollowedId = usernameFollowedId;
        this.usernameFollowingId = usernameFollowingId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsernameFollowedId() {
        return usernameFollowedId;
    }

    public void setUsernameFollowedId(Long usernameFollowedId) {
        this.usernameFollowedId = usernameFollowedId;
    }

    public Long getUsernameFollowingId() {
        return usernameFollowingId;
    }

    public void setUsernameFollowingId(Long usernameFollowingId) {
        this.usernameFollowingId = usernameFollowingId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
}
