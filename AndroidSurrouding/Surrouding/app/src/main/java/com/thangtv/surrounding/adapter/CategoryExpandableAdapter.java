package com.thangtv.surrounding.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.thangtv.surrounding.R;
import com.thangtv.surrounding.model.CategoryChild;
import com.thangtv.surrounding.model.CategoryParent;


import java.util.List;

/**
 * Created by uendno on 17/11/2015.
 */
public class CategoryExpandableAdapter extends ExpandableRecyclerAdapter<CategoryExpandableAdapter.CategoryParentViewHolder, CategoryExpandableAdapter.CategoryChildViewHolder> {

    private LayoutInflater inflater;
    public boolean checkList[][];
    private int currentPosition;

    public CategoryExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
        Log.d("TEST_LIST", parentItemList.size() + " ");
    }

    @Override
    public CategoryParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {

        View viewItem = inflater.inflate(R.layout.item_recycler_category_parent, viewGroup, false);
        return new CategoryParentViewHolder(viewItem);
    }

    @Override
    public CategoryChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View itemView = inflater.inflate(R.layout.item_recycler_category_child, viewGroup, false);
        return new CategoryChildViewHolder(itemView);
    }

    @Override
    public void onBindParentViewHolder(CategoryParentViewHolder crimeParentViewHolder, int i, Object o) {
        CategoryParent categoryParent = (CategoryParent) o;
        crimeParentViewHolder.parentCategory.setText(categoryParent.getTitle());
        Log.d("TEST_ADAPTER", i + " " + categoryParent.getTitle());
    }

    @Override
    public void onBindChildViewHolder(CategoryChildViewHolder crimeChildViewHolder, int i, Object o) {
        CategoryChild crimeChild = (CategoryChild) o;
        crimeChildViewHolder.childCategory.setText(crimeChild.getTitle().toString());
        crimeChildViewHolder.checkBox.setChecked(crimeChild.isChecked());
    }

    public class CategoryParentViewHolder extends ParentViewHolder {
        public TextView parentCategory;
        public ImageButton parentExpandArrow;

        public CategoryParentViewHolder(View itemView) {
            super(itemView);

            parentCategory = (TextView) itemView.findViewById(R.id.parent_category);
            parentExpandArrow = (ImageButton) itemView.findViewById(R.id.parent_expand_arrow);
        }
    }

    public class CategoryChildViewHolder extends ChildViewHolder {
        public TextView childCategory;
        public CheckBox checkBox;

        public CategoryChildViewHolder(View viewItem) {
            super(viewItem);

            childCategory = (TextView) itemView.findViewById(R.id.child_category);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
        }
    }
}
