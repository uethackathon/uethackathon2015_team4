package com.thangtv.surrounding.network.service;

import com.thangtv.surrounding.network.model.GetAllSubject;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by tuantmtb on 21/11/2015.
 */
public interface IGetAllSubject {
    @GET("/hackathon/subject/getAllSubject")
    Call<GetAllSubject> getAllSubject();
}
