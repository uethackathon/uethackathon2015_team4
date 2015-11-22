package com.thangtv.surrounding.ui.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.adapter.CategoryChildAdapter;
import com.thangtv.surrounding.model.CategoryChild;
import com.thangtv.surrounding.network.model.subject.Data;
import com.thangtv.surrounding.ui.activity.ChooseCategoryActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesListFragment extends android.support.v4.app.Fragment {

    RecyclerView recyclerView;
    List<Data> categoryChildList;
    public CategoryChildAdapter adapter;

    public static CategoriesListFragment newInstance(int index) {
        CategoriesListFragment f = new CategoriesListFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_categories_list, container,
                false);
        Log.d("TEST_FRAGMENT","ok");
        recyclerView = (RecyclerView) v.findViewById(R.id.category_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle args = getArguments();
        int index = args.getInt("index", 0);

        categoryChildList = ((ChooseCategoryActivity) getActivity()).categories.get(index);
        adapter = new CategoryChildAdapter(getActivity(), categoryChildList);
        recyclerView.setAdapter(adapter);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
