package com.thangtv.surrounding.apis;

import com.thangtv.surrounding.network.model.like.LikeContainer;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by uendno on 22/11/2015.
 */
public interface ILike {
    @GET("/hackathon/post/like")
    Call<LikeContainer> postLike(@Query("user_id") String user_id, @Query("post_id") String post_id);
}
