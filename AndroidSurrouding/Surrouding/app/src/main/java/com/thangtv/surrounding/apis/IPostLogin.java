package com.thangtv.surrounding.apis;


import com.thangtv.surrounding.network.model.user.PostLogin;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by tuantmtb on 21/11/2015.
 */
public interface IPostLogin {
    @FormUrlEncoded
    @POST("/hackathon/user/login")
    Call<PostLogin> postLogin(@Field("email") String email, @Field("password") String password);
}
