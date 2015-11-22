package com.thangtv.surrounding.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.network.model.postNearBy.Post;
import com.thangtv.surrounding.network.model.postNearBy.PostContainer;
import com.thangtv.surrounding.ui.activity.PostDetailsActivity;
import com.thangtv.surrounding.ui.helper.ImageLoadTask;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by uendno on 21/11/2015.
 */
public class PostNearByAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> posts;
    private Context context;
    private int currentPosition;

    public PostNearByAdapter(Context context, PostContainer postContainer) {
        posts = postContainer.getData();
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recycle_post, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        currentPosition = position;
        final PostViewHolder postHolder = (PostViewHolder) viewHolder;
        Post post = posts.get(position);
        if (post.isLiked()) {
            postHolder.buttonLike.setText("LIKED");
            postHolder.buttonLike.setTextColor(context.getResources().getColor(R.color.color_disabled_text));
        }

        new ImageLoadTask(posts.get(position).getUser().getAvatar(), postHolder.avatar);
        postHolder.userName.setText(posts.get(position).getUser().getUsername());
        postHolder.content.setText(posts.get(position).getContent());
        int likeCount = Integer.parseInt(post.getPostLikeCount());
        int commentCount = Integer.parseInt(post.getPostCommentCount());
        postHolder.likesAndComments.setText(likeCount +" like(s)     " + commentCount+ " comment(s)");

    }


    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CircleImageView avatar;
        private TextView userName;
        private TextView content;
        private TextView likesAndComments;
        private Button buttonLike;


        public PostViewHolder(final View parent) {
            super(parent);
            avatar = (CircleImageView) parent.findViewById(R.id.avatar);
            userName = (TextView) parent.findViewById(R.id.user_name);
            content = (TextView) parent.findViewById(R.id.content);
            buttonLike = (Button) parent.findViewById(R.id.button_like);
            likesAndComments = (TextView) parent.findViewById(R.id.likes_and_comments);

            buttonLike.setOnClickListener(this);
            content.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.content:
                    Intent intent = new Intent(context, PostDetailsActivity.class);
                    intent.putExtra("postID", posts.get(currentPosition).getPostId());
                    context.startActivity(intent);
                    break;
                case R.id.button_like:



                    break;
            }
        }
    }


    public void swapData (PostContainer postContainer) {
        this.posts = postContainer.getData();
        notifyDataSetChanged();
    }

}
