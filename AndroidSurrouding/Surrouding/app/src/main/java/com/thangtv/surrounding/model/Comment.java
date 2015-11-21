package com.thangtv.surrounding.model;

import java.util.Calendar;

/**
 * Created by uendno on 21/11/2015.
 */
public class Comment {
    private User user;
    private String content;
    private Calendar createdAt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }
}
