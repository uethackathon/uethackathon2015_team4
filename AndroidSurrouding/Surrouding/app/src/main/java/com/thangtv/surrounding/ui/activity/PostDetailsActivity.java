package com.thangtv.surrounding.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.adapter.PostDetailsAdapter;
import com.thangtv.surrounding.apis.IGetPostDetails;
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.network.model.postDetails.PostDetailsContainer;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PostDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String postID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);


        Intent intent = getIntent();
        postID = intent.getStringExtra("postID");

        recyclerView = (RecyclerView) findViewById(R.id.post_details_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.URI_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IGetPostDetails service = retrofit.create(IGetPostDetails.class);

        Call<PostDetailsContainer> call = service.getPostByID(postID, ""+Var.currentUser.getId());


        call.enqueue(new Callback<PostDetailsContainer>() {
            @Override
            public void onResponse(Response<PostDetailsContainer> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    PostDetailsContainer postDetailsContainer = response.body();
                    PostDetailsAdapter adapter = new PostDetailsAdapter(getApplicationContext(), postDetailsContainer);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(PostDetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(PostDetailsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
