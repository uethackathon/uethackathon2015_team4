package com.thangtv.surrounding.apis;

import com.thangtv.surrounding.network.model.postNearBy.PostContainer;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by uendno on 21/11/2015.
 */
public interface IGetNearByPosts {
    ///hackathon/post/getPostNearby?lat=21.0031&lng=105.82&user_id=1&offset=0&limit=3

    @GET("/hackathon/post/getPostNearby")
    Call<PostContainer> getPostNearby(@Query("lat") String lat, @Query("lng") String lng
            , @Query("user_id") String user_id
            , @Query("offset") String offset, @Query("limit") String limit, @Query("radius") String radius);
}
