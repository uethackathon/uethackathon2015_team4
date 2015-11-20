package com.thangtv.surrounding.model;

import java.util.Date;

/**
 * Created by uendno on 17/11/2015.
 */
public class CategoryChild {

    private String title;
    private boolean isChecked;

    public CategoryChild(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
