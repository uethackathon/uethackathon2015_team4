
package com.thangtv.surrounding.network.model.postDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Comment {

    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     *
     * @return
     *     The user
     */
    public User_ getUser() {
        return user;
    }

    /**
     *
     * @param user
     *     The user
     */
    public void setUser(User_ user) {
        this.user = user;
    }

    /**
     *
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
