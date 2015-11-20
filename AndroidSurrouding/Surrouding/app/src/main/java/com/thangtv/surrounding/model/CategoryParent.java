package com.thangtv.surrounding.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by uendno on 17/11/2015.
 */
public class CategoryParent implements ParentObject {

    private List<Object> mChildrenList;
    private String title;
    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
