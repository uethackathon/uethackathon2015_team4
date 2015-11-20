package com.thangtv.surrounding.apis;

import android.location.LocationManager;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by uendno on 20/11/2015.
 */
public class Apis {
    /**
     * GoogleApiClient wraps our service connection to Google Play Services and provides access
     * to the user's sign in state as well as the Google's APIs.
     */
    public static GoogleApiClient mGoogleApiClient;

    public static LocationManager locationManager;
}
