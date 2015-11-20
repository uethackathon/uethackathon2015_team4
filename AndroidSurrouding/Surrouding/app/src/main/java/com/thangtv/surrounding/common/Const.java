package com.thangtv.surrounding.common;

import android.os.Environment;

/**
 * Created by uendno on 14-Nov-15.
 */
public class Const {

    //External storage directory
    public static final String appPath = Environment.getExternalStorageDirectory().toString()+"/surrounding/";

    //image path
    public static final String imagePath = appPath+"image/";

    // The minimum distance to change Updates in meters
    public static final long PLACE_MIN_DISTANCE_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    public static final long PLACE_MIN_TIME_UPDATES = 1000 * 60 * 1; // 1 minute

    // RC choose location activity
    public static final int RC_CHOOSE_LOCATION = 1;

    //RC choose category activity
    public static final int RC_CHOOSE_CATEFORY = 2;

}
