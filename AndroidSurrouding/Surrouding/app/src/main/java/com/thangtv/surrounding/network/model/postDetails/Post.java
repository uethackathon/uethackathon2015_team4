
package com.thangtv.surrounding.network.model.postDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Post {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("post_comment_count")
    @Expose
    private String postCommentCount;
    @SerializedName("post_like_count")
    @Expose
    private String postLikeCount;
    @SerializedName("is_like")
    @Expose
    private boolean isLike;
    @SerializedName("subjects")
    @Expose
    private List<String> subjects = new ArrayList<String>();
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = new ArrayList<Comment>();

    /**
     * 
     * @return
     *     The user
     */
    public User getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The postId
     */
    public String getPostId() {
        return postId;
    }

    /**
     * 
     * @param postId
     *     The post_id
     */
    public void setPostId(String postId) {
        this.postId = postId;
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

    /**
     * 
     * @return
     *     The postCommentCount
     */
    public String getPostCommentCount() {
        return postCommentCount;
    }

    /**
     * 
     * @param postCommentCount
     *     The post_comment_count
     */
    public void setPostCommentCount(String postCommentCount) {
        this.postCommentCount = postCommentCount;
    }

    /**
     * 
     * @return
     *     The postLikeCount
     */
    public String getPostLikeCount() {
        return postLikeCount;
    }

    /**
     * 
     * @param postLikeCount
     *     The post_like_count
     */
    public void setPostLikeCount(String postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    /**
     * 
     * @return
     *     The isLike
     */
    public boolean isIsLike() {
        return isLike;
    }

    /**
     * 
     * @param isLike
     *     The is_like
     */
    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    /**
     * 
     * @return
     *     The subjects
     */
    public List<String> getSubjects() {
        return subjects;
    }

    /**
     * 
     * @param subjects
     *     The subjects
     */
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    /**
     * 
     * @return
     *     The comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
