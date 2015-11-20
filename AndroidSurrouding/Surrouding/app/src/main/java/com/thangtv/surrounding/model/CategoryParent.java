package com.thangtv.surrounding.model;

import java.util.ArrayList;
import java.util.List;


public class CategoryParent {
    private String title;
    private List<CategoryChild> categoryChildList;

    public CategoryParent() {
        title = null;
        categoryChildList = new ArrayList<>();
    }

    public CategoryParent(String title, List<CategoryChild> categoryChildList) {
        this.title = title;
        this.categoryChildList = categoryChildList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CategoryChild> getCategoryChildList() {
        return categoryChildList;
    }

    public void setCategoryChildList(List<CategoryChild> categoryChildList) {
        this.categoryChildList = categoryChildList;
    }
}
