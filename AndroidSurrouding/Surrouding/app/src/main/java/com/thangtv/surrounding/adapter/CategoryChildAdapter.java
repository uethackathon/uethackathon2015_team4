package com.thangtv.surrounding.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRouter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.model.CategoryChild;
import com.thangtv.surrounding.model.CategoryParent;
import com.thangtv.surrounding.network.model.subject.Data;
import com.thangtv.surrounding.ui.fragment.CategoriesListFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Data> list;
    private Context context;
    private int currentPosition;

    public CategoryChildAdapter(Context context, List<Data> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recycler_category, parent, false);
        return new CategoryChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final CategoryChildViewHolder holder = (CategoryChildViewHolder) viewHolder;
        currentPosition = position;
        holder.title.setText(list.get(position).getTitle());
        if (Var.selectedCategoryIDs==null) {
            Var.selectedCategoryIDs = new ArrayList<>();
        }
        for (int i = 0; i < Var.selectedCategoryIDs.size(); i++) {
            if (Var.selectedCategoryIDs.get(i).equals(list.get(position).getSubjectId())) {
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setChecked(false);
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class CategoryChildViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private CheckBox checkBox;

        public CategoryChildViewHolder(final View parent) {
            super(parent);

            checkBox = (CheckBox) parent.findViewById(R.id.check_box);
            title = (TextView) parent.findViewById(R.id.category_title);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Var.selectedCategoryIDs.add(list.get(currentPosition).getSubjectId());
                    } else {
                        for (int i = 0; i < Var.selectedCategoryIDs.size(); i++) {
                            if (Var.selectedCategoryIDs.get(i) == list.get(currentPosition).getSubjectId()) {
                                Var.selectedCategoryIDs.remove(i);
                            }
                        }
                    }
                }
            });

        }


    }
}
