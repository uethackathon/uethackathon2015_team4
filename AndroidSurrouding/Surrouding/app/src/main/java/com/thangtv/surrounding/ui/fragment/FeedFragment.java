package com.thangtv.surrounding.ui.fragment;


import android.location.Location;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.adapter.PostNearByAdapter;
import com.thangtv.surrounding.apis.IGetNearByPosts;
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.controller.PlaceData;
import com.thangtv.surrounding.network.model.postNearBy.PostContainer;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_feed, container, false);



        recyclerView = (RecyclerView) v.findViewById(R.id.post_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Location myLocation = PlaceData.getGpsData(getActivity());
        String lat = Double.toString(myLocation.getLatitude());
        String lng = Double.toString(myLocation.getLongitude());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.URI_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IGetNearByPosts service = retrofit.create(IGetNearByPosts.class);

        Call<PostContainer> call = service.getPostNearby(lat,lng, Integer.toString(Var.currentUser.getId()), "0", "10", Integer.toString(Var.radius));

        call.enqueue(new Callback<PostContainer>() {
                         @Override
                         public void onResponse(Response<PostContainer> response, Retrofit retrofit) {


                             PostNearByAdapter adapter = new PostNearByAdapter(getActivity(), response.body());
                             recyclerView.setAdapter(adapter);
                         }

                         @Override
                         public void onFailure(Throwable t) {

                         }
                     }

        );
        return v;
    }


}
