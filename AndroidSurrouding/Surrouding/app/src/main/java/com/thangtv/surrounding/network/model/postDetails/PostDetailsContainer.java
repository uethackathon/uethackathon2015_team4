
package com.thangtv.surrounding.network.model.postDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PostDetailsContainer {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("post")
    @Expose
    private Post post;
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
     *     The post
     */
    public Post getPost() {
        return post;
    }

    /**
     * 
     * @param post
     *     The post
     */
    public void setPost(Post post) {
        this.post = post;
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
