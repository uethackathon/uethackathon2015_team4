package com.thangtv.surrounding.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.network.model.postDetails.Comment;
import com.thangtv.surrounding.network.model.postDetails.PostDetailsContainer;
import com.thangtv.surrounding.ui.helper.ImageLoadTask;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
        comments = postDetailsContainer.getPost().getComments();
    }

    @Override
    public int getItemCount() {
        return comments.size() + 1;
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

        }
        return null;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
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

                break;
            case 2:
                final CommentViewHolder holder = (CommentViewHolder) viewHolder;
                currentPosition = position - 1;
                new ImageLoadTask(postDetailsContainer.getPost().getComments().get(currentPosition).getUser().getAvatar(), holder.avatar);
                holder.name.setText(postDetailsContainer.getPost().getUser().getUsername());
                holder.content.setText(postDetailsContainer.getPost().getComments().get(currentPosition).getContent());
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
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.avatar:
                case R.id.user_name:

                    break;
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
            name = (TextView) parent.findViewById(R.id.name);
            content = (TextView) parent.findViewById(R.id.content);
            container = (View) parent.findViewById(R.id.container);

            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.container:

                    break;
            }
        }
    }
}
