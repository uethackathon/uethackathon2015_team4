package com.thangtv.surrounding.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Route;
import com.thangtv.surrounding.R;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.model.Comment;
import com.thangtv.surrounding.model.Post;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by uendno on 21/11/2015.
 */
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> posts;
    private int currentPosition;
    private Context context;
    private List<Boolean> likeList;

    public PostAdapter(Context context, List<Post> posts) {
        this.posts = posts;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recycle_post, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final PostViewHolder holder = (PostViewHolder) viewHolder;

        currentPosition = position;

        Post post = posts.get(position);
        holder.avatar.setImageBitmap(post.getUser().getAvatar());
        holder.userName.setText(post.getUser().getName());
        holder.content.setText(post.getContent());

        int likeCount = post.getLikers().size();
        int commentCount = post.getComments().size();
        holder.likesAndComments.setText(likeCount + " likes    " + commentCount + " comments");

        for (int i = 0; i < post.getLikers().size(); i++) {
            if (post.getLikers().get(i).getId() == Var.currentUser.getId()) {
                holder.buttonLike.setText("LIKED");
                holder.buttonLike.setTextColor(context.getResources().getColor(R.color.color_disabled_text));
                break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CircleImageView avatar;
        public TextView userName;
        public TextView content;
        public Button buttonLike;
        public TextView likesAndComments;

        public PostViewHolder(final View parent) {
            super(parent);

            avatar = (CircleImageView) parent.findViewById(R.id.avatar);
            userName = (TextView) parent.findViewById(R.id.user_name);
            content = (TextView) parent.findViewById(R.id.content);
            buttonLike = (Button) parent.findViewById(R.id.button_like);
            likesAndComments = (TextView) parent.findViewById(R.id.likes_and_comments);

            likesAndComments.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.likes_and_comments:

                    if (buttonLike.getText().toString().equals("LIKE")) {
                        posts.get(currentPosition).getLikers().add(Var.currentUser);
                        buttonLike.setText("LIKED");
                        buttonLike.setTextColor(context.getResources().getColor(R.color.color_disabled_text));
                        int likeCount = posts.get(currentPosition).getLikers().size() + 1;
                        int commentCount = posts.get(currentPosition).getComments().size();
                        likesAndComments.setText(likeCount + " likes    " + commentCount + " comments");
                    } else if (buttonLike.getText().toString().equals("LIKED")) {

                        for (int i = 0; i < posts.get(currentPosition).getLikers().size(); i++) {
                            if (posts.get(currentPosition).getLikers().get(i).getId() == Var.currentUser.getId()) {
                                posts.get(currentPosition).getLikers().remove(i);
                                buttonLike.setText("LIKE");
                                buttonLike.setTextColor(context.getResources().getColor(R.color.color_accent));
                            }
                        }
                    }

                    break;
            }
        }
    }
}

