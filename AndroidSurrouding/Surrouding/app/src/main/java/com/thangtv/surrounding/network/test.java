package com.thangtv.surrounding.network;

import com.thangtv.surrounding.network.model.GetAllSubject;
import com.thangtv.surrounding.network.service.IGetAllSubject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tuantmtb on 21/11/2015.
 */
public class test {
    public static final String API_URL = "http://project.huynguyenis.me";

    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IGetAllSubject service = retrofit.create(IGetAllSubject.class);

        Call<GetAllSubject> call = service.getAllSubject();

        call.enqueue(new Callback<GetAllSubject>() {
            @Override
            public void onResponse(Response<GetAllSubject> response, Retrofit retrofit) {
                System.out.println("status code: " + response.code());
                if (!response.isSuccess()) {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                GetAllSubject listSubject = response.body();
                if (listSubject == null) return;
                System.out.println("respone info");
                System.out.println("status: " + listSubject.getStatus());
                for (int pos = 0; pos < listSubject.getData().size(); pos++) {
                    System.out.println("data: " + pos + " :" + listSubject.getData().get(pos).getTitle());
                }


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
