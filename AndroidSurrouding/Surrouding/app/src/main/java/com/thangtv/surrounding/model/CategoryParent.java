package com.thangtv.surrounding.model;

import java.util.ArrayList;
import java.util.List;


public class CategoryParent {
    private int id;
    private String title;
    private List<CategoryChild> categoryChildList;

    public CategoryParent() {
        title = null;
        categoryChildList = new ArrayList<>();
    }

    public CategoryParent(int id) {
        this.id = id;
    }

    public CategoryParent(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public CategoryParent(String title, List<CategoryChild> categoryChildList) {
        this.title = title;
        this.categoryChildList = categoryChildList;
    }

    public CategoryParent(int id, String title, List<CategoryChild> categoryChildList) {
        this.id = id;
        this.title = title;
        this.categoryChildList = categoryChildList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
