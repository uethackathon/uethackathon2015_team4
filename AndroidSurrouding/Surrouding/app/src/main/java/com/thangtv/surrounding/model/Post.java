package com.thangtv.surrounding.model;

import java.util.Calendar;
import java.util.List;

/**
 * Created by uendno on 21/11/2015.
 */
public class Post {

    private int id;
    private User user;
    private List<User> followers;
    private String description;
    private String locationName;
    private String locationLat;
    private String locationLng;
    private List<CategoryChild> categories;
    private Calendar createdAt;

}
