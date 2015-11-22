package com.thangtv.surrounding.common;

import android.os.Environment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by uendno on 14-Nov-15.
 */
public class Const {

    //External storage directory
    public static final String appPath = Environment.getExternalStorageDirectory().toString() + "/surrounding/";

    //image path
    public static final String imagePath = appPath + "image/";

    // The minimum distance to change Updates in meters
    public static final long PLACE_MIN_DISTANCE_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    public static final long PLACE_MIN_TIME_UPDATES = 1000 * 60 * 1; // 1 minute

    // RC choose location activity
    public static final int RC_CHOOSE_LOCATION = 1;

    //RC choose category activity
    public static final int RC_CHOOSE_CATEFORY = 2;

    //RC edit profle
    public static final int RC_EDIT_PROFILE = 3;

    public static final int RC_AVATAR_REQUEST_CAMERA = 4;

    public static final int RC_AVATAR_SELECT_FILE = 5;

    public static final int RC_AVATAR_CROP = 6;

    public static final int RC_COVER_REQUEST_CAMERA = 4;

    public static final int RC_COVER_SELECT_FILE = 5;

    public static final int RC_COVER_CROP = 6;


    // Bound the suggested result in Viet Nam first
    public static final LatLngBounds MAP_BOUNDS_NORTH_VIETNAM = new LatLngBounds(
            new LatLng(20.814116, 103.706864), new LatLng(22.697388, 106.589234));

    public static final String KEY_USER_ID = "userID";
    public static final String KEY_NAME = "name";
    public static final String KEY_DOB = "dob";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_AVATAR = "avatar";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USER = "user";
    public static final String KEY_CAREER = "career";

    public static final String URI_API = "http://project.huynguyenis.me";

}