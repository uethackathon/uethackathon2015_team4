package com.thangtv.surrounding.network.service;

import com.squareup.okhttp.RequestBody;
import com.thangtv.surrounding.network.model.upload.Image;
import com.thangtv.surrounding.network.model.user.GetUserProfile;
import com.thangtv.surrounding.network.model.user.PostLogin;
import com.thangtv.surrounding.network.model.register.PostRegister;
import com.thangtv.surrounding.network.model.subject.GetAllSubject;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by tuantmtb on 21/11/2015.
 */
public interface ServiceImplements {
    @FormUrlEncoded
    @POST("/hackathon/user/login")
    Call<PostLogin> postLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/hackathon/user/register")
    Call<PostRegister> postSignIn(@Field("email") String email, @Field("password") String password
            ,@Field("first_name") String first_name,@Field("date")String date
            ,@Field("phone")String phone,@Field("career") String career
            ,@Field("gender")String gender,@Field("description") String description
            ,@Field("image") String avatarurl);


    @FormUrlEncoded
    @POST("/hackathon/post/add")
    Call<PostRegister> postPost(@Field("user_id") String user_id, @Field("content") String content
            ,@Field("subject") String subject,@Field("lat")String lat
            ,@Field("lng")String lng,@Field("name") String name);

    @FormUrlEncoded
    @POST("/hackathon/post/comment")
    Call<PostRegister> postComment(@Field("user_id") String user_id, @Field("post_id") String post_id
            ,@Field("content") String content);

    @GET("/hackathon/subject/getallsubject")
    Call<GetAllSubject> getAllSubject();

    @GET("hackathon/user/getuserprofile")
    Call<GetUserProfile> getUserProfile(@Query("user_id") String user_id);

    @Multipart
    @POST("/hackathon/post/UploadImage")
    Call<Image> uploadImage(
            @Part("image\"; filename=\"image.jpg\" \"") RequestBody file);


}
