
package com.campus.ledrian.interation.domain;

public class NotificationDTO {
    private Long id;
    private Long receiver;
    private Long giver;
    private Long type;
    private boolean check;

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


    
    
}
