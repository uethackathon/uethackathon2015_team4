
package com.thangtv.surrounding.network.model.postDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PostDetailsContainer {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private Post data;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     *
     * @return
     *     The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     *     The data
     */
    public Post getPost() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setPost(Post data) {
        this.data = data;
    }

    /**
     *
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
