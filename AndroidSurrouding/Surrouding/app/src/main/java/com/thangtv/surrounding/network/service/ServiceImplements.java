package com.thangtv.surrounding.network.service;

import com.google.android.gms.wearable.DataApi;
import com.squareup.okhttp.RequestBody;
import com.thangtv.surrounding.network.model.login.PostLogin;
import com.thangtv.surrounding.network.model.register.PostRegister;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;

/**
 * Created by tuantmtb on 21/11/2015.
 */
public interface ServiceImplements {
    @FormUrlEncoded
    @POST("/hackathon/user/login")
    Call<PostLogin> postLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/hackathon/user/register")
    Call<PostRegister> postLogin(@Field("email") String email, @Field("password") String password
            ,@Field("first_name") String first_name,@Field("date")String date
            ,@Field("phone")String phone,@Field("career") String career
            ,@Field("gender")String gender,@Field("description") String description);

}
