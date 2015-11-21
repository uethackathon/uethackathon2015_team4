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

    @Multipart
    @POST("/hackathon/user/register")
    Call<PostRegister> postLoginWithImage(@Part("image\"; filename=\"image.jpg\" \"") RequestBody file
            ,@Path("email") String email, @Path("password") String password
            ,@Path("first_name") String first_name,@Path("date")String date
            ,@Path("phone")String phone,@Path("career") String career
            ,@Path("gender")String gender,@Path("description") String description);

    @FormUrlEncoded
    @POST("/hackathon/post/add")
    Call<PostRegister> postPost(@Field("user_id") String user_id, @Field("content") String content
            ,@Field("subject") String subject,@Field("lat")String lat
            ,@Field("lng")String lng,@Field("name") String name);
}
