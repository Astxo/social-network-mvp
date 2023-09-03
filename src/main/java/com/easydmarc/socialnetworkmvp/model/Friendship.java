package com.easydmarc.socialnetworkmvp.model;

import jakarta.persistence.*;

@Entity
public class Friendship {

    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn
    private Integer userId;

    private Integer friendId;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    public Friendship() {
    }

    public Friendship(Integer userId, Integer friendId, RequestStatus requestStatus) {
        this.userId = userId;
        this.friendId = friendId;
        this.requestStatus = requestStatus;
    }

    public Friendship(Integer id, Integer userId, Integer friendId, RequestStatus requestStatus) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.requestStatus = requestStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
