
package com.thangtv.surrounding.network.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Message {

    @SerializedName("is_liked")
    @Expose
    private int isLiked;

    /**
     * 
     * @return
     *     The isLiked
     */
    public int getIsLiked() {
        return isLiked;
    }

    /**
     * 
     * @param isLiked
     *     The is_liked
     */
    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

}
