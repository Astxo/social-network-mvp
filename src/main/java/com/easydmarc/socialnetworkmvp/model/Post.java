package com.easydmarc.socialnetworkmvp.model;

import com.easydmarc.socialnetworkmvp.helper.HashMapConverter;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Map;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private Date createdOn;

    private Date updatedOn;

    private Integer ownerId;

    private Integer friendId;

    @Convert(converter = HashMapConverter.class)
    private Map<String, String> content;

    public Post() {
    }

    public Post(Integer id, Date createdOn, Integer ownerId, Integer friendId, Map<String, String> content) {
        this.id = id;
        this.createdOn = createdOn;
        this.ownerId = ownerId;
        this.friendId = friendId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}
