package com.thangtv.surrounding.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.thangtv.surrounding.R;
import com.thangtv.surrounding.adapter.CategoryExpandableAdapter;
import com.thangtv.surrounding.model.CategoryChild;
import com.thangtv.surrounding.model.CategoryParent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChooseCategory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private List<ParentObject> categoryParents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        //set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set up fake data
        categoryParents = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CategoryParent categoryParent = new CategoryParent();
            List<Object> categoryChilds = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                CategoryChild categoryChild;
                categoryChild = new CategoryChild("child");
                categoryChilds.add(categoryChild);
            }
            categoryParent.setChildObjectList(categoryChilds);
            categoryParent.setTitle("Fucking Idiot");
            categoryParents.add(categoryParent);
        }

        //set up recycler view
        recyclerView = (RecyclerView) findViewById(R.id.category_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CategoryExpandableAdapter adapter = new CategoryExpandableAdapter(this, categoryParents);
        adapter.setCustomParentAnimationViewId(R.id.parent_expand_arrow);
        adapter.setParentAndIconExpandOnClick(true);
        recyclerView.setAdapter(adapter);
    }
}
