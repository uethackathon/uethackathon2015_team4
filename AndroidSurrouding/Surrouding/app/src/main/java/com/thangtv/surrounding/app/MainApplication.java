package com.thangtv.surrounding.app;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.thangtv.surrounding.apis.Apis;

/**
 * Created by uendno on 20/11/2015.
 */
public class MainApplication extends Application {
    // Construct a GoogleApiClient for the {@link Places#GEO_DATA_API} using AutoManage
    // functionality, which automatically sets up the API client to handle Activity lifecycle
    // events. If your activity does not extend FragmentActivity, make sure to call connect()
    // and disconnect() explicitly.

    @Override
    public void onCreate() {
        super.onCreate();

        Apis.mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .build();

        Apis.mGoogleApiClient.connect();

        Apis.locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
    }
}
