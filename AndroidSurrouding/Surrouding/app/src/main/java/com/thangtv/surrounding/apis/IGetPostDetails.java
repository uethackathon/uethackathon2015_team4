package com.thangtv.surrounding.apis;


import com.thangtv.surrounding.network.model.postDetails.PostDetailsContainer;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by uendno on 22/11/2015.
 */
public interface IGetPostDetails {
    @GET("/hackathon/post/getpost")
    Call<PostDetailsContainer> getPostByID(@Query("post_id") String postID, @Query("user_id") String userID);
}
