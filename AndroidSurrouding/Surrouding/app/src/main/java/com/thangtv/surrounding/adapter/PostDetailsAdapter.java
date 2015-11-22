package com.thangtv.surrounding.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.apis.IGetNearByPosts;
import com.thangtv.surrounding.apis.IGetPostDetails;
import com.thangtv.surrounding.apis.ILike;
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.network.model.like.LikeContainer;
import com.thangtv.surrounding.network.model.postDetails.Comment;
import com.thangtv.surrounding.network.model.postDetails.PostDetailsContainer;
import com.thangtv.surrounding.network.model.postNearBy.PostContainer;
import com.thangtv.surrounding.network.model.register.PostRegister;
import com.thangtv.surrounding.network.service.ServiceImplements;
import com.thangtv.surrounding.ui.activity.ProfileActivity;
import com.thangtv.surrounding.ui.helper.ImageLoadTask;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by uendno on 21/11/2015.
 */
public class PostDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PostDetailsContainer postDetailsContainer;
    private Context context;
    private int currentPosition;
    private List<Comment> comments;

    public PostDetailsAdapter(Context context, PostDetailsContainer postDetailsContainer) {

        this.context = context;
        this.postDetailsContainer = postDetailsContainer;
        if (postDetailsContainer.getPost().getComments() != null) {
            comments = postDetailsContainer.getPost().getComments();
        } else {
            comments = new ArrayList<>();
        }
    }

    @Override
    public int getItemCount() {
        return comments.size() + 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 1:
                View itemView = inflater.inflate(R.layout.item_recycler_post_details, parent, false);
                return new PostViewHolder(itemView);

            case 2:
                View itemView1 = inflater.inflate(R.layout.item_recycler_comment, parent, false);
                return new CommentViewHolder(itemView1);
            case 3:
                View itemView2 = inflater.inflate(R.layout.item_recycler_add_comment, parent, false);
                return new AddCommentViewHolder(itemView2);

        }
        return null;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else if (position == 1) {
            return 3;
        } else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        Log.d("TEST_ADAPTER", Integer.toString(position));
        switch (getItemViewType(position)) {

            case 1:
                //get holder
                final PostViewHolder postHolder = (PostViewHolder) viewHolder;

                postHolder.userName.setText(postDetailsContainer.getPost().getUser().getUsername());

                new ImageLoadTask(postDetailsContainer.getPost().getUser().getAvatar(), postHolder.avatar);

                postHolder.content.setText(postDetailsContainer.getPost().getContent());

                int likeCount = Integer.parseInt(postDetailsContainer.getPost().getPostLikeCount());
                int commentCount = Integer.parseInt(postDetailsContainer.getPost().getPostCommentCount());

                postHolder.likesAnhComments.setText(likeCount + " like(s)     " + commentCount + " comment(s)");

                if (postDetailsContainer.getPost().isIsLike()) {
                    postHolder.likeButton.setText("LIKED");
                    postHolder.likeButton.setTextColor(context.getResources().getColor(R.color.color_disabled_text));
                }

                break;
            case 2:
                final CommentViewHolder holder = (CommentViewHolder) viewHolder;
                currentPosition = position - 2;
                new ImageLoadTask(comments.get(currentPosition).getUser().getAvatar(), holder.avatar);
                holder.name.setText(comments.get(currentPosition).getUser().getUsername());
                holder.content.setText(comments.get(currentPosition).getContent());
                break;
            case 3:
                final AddCommentViewHolder holder1 = (AddCommentViewHolder) viewHolder;
                holder1.avatar.setImageBitmap(Var.currentUser.getAvatar());
                holder1.name.setText(Var.currentUser.getName());
                break;

        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CircleImageView avatar;
        public TextView userName;
        public TextView content;
        public Button likeButton;
        public TextView likesAnhComments;

        public PostViewHolder(final View parent) {
            super(parent);

            avatar = (CircleImageView) parent.findViewById(R.id.avatar);
            userName = (TextView) parent.findViewById(R.id.user_name);
            content = (TextView) parent.findViewById(R.id.content);
            likeButton = (Button) parent.findViewById(R.id.button_like);
            likesAnhComments = (TextView) parent.findViewById(R.id.likes_and_comments);

            avatar.setOnClickListener(this);
            userName.setOnClickListener(this);
            likeButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.avatar:
                case R.id.user_name:

                    break;
                case R.id.button_like:
                    like();
                    if (likeButton.getText().toString().equals("LIKE")) {
                        likeButton.setText("LIKED");
                        likeButton.setTextColor(context.getResources().getColor(R.color.color_disabled_text));
                    } else {
                        likeButton.setText("LIKE");
                        likeButton.setTextColor(context.getResources().getColor(R.color.color_accent));
                    }
            }
        }
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CircleImageView avatar;
        public TextView name;
        public TextView content;
        public View container;

        public CommentViewHolder(final View parent) {
            super(parent);

            avatar = (CircleImageView) parent.findViewById(R.id.avatar);
            name = (TextView) parent.findViewById(R.id.user_name);
            content = (TextView) parent.findViewById(R.id.content);
            container = (View) parent.findViewById(R.id.container);

            avatar.setOnClickListener(this);
            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.avatar:
                case R.id.name:
                    Intent intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("userID", Integer.parseInt(comments.get(currentPosition).getUser().getUserId()));
                    context.startActivity(intent);
            }
        }
    }

    public class AddCommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CircleImageView avatar;
        public TextView name;
        public EditText content;
        public Button addButton;

        public AddCommentViewHolder(final View parent) {
            super(parent);

            avatar = (CircleImageView) parent.findViewById(R.id.avatar);
            name = (TextView) parent.findViewById(R.id.user_name);
            content = (EditText) parent.findViewById(R.id.content);
            addButton = (Button) parent.findViewById(R.id.button_add);

            addButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_add:

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Const.URI_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ServiceImplements service = retrofit.create(ServiceImplements.class);

                    Call<PostRegister> call = service.postComment(Var.currentUser.getId() + "", postDetailsContainer.getPost().getPostId(), content.getText().toString());

                    call.enqueue(new Callback<PostRegister>() {
                                     @Override
                                     public void onResponse(Response<PostRegister> response, Retrofit retrofit) {

                                         PostRegister postRegister = response.body();

                                         if (postRegister.getStatus() == 1) {

                                             Retrofit retrofit1 = new Retrofit.Builder()
                                                     .baseUrl(Const.URI_API)
                                                     .addConverterFactory(GsonConverterFactory.create())
                                                     .build();
                                             IGetPostDetails service = retrofit1.create(IGetPostDetails.class);

                                             Call<PostDetailsContainer> call = service.getPostByID(postDetailsContainer.getPost().getPostId(), "" + Var.currentUser.getId());


                                             call.enqueue(new Callback<PostDetailsContainer>() {
                                                 @Override
                                                 public void onResponse(Response<PostDetailsContainer> response, Retrofit retrofit) {
                                                     if (response.isSuccess()) {
                                                         Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                                         PostDetailsContainer newResult = response.body();
                                                         postDetailsContainer = newResult;
                                                         notifyDataSetChanged();
                                                     } else {

                                                     }
                                                 }

                                                 @Override
                                                 public void onFailure(Throwable t) {

                                                 }
                                             });


                                         }
                                     }

                                     @Override
                                     public void onFailure(Throwable t) {

                                     }
                                 }

                    );


                    break;
            }
        }
    }


    public void like() {

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Const.URI_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ILike service = retrofit1.create(ILike.class);

        Call<LikeContainer> call = service.postLike(Var.currentUser.getId()+" ", postDetailsContainer.getPost().getPostId());


        call.enqueue(new Callback<LikeContainer>() {
            @Override
            public void onResponse(Response<LikeContainer> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                    LikeContainer result = response.body();

                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
