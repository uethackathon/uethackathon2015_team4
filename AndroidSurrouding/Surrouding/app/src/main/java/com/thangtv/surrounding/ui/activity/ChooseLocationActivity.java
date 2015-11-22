package com.thangtv.surrounding.ui.activity;

import android.content.Intent;
import android.location.Location;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thangtv.surrounding.R;
import com.thangtv.surrounding.adapter.MapPlaceAutocompleteAdapter;
import com.thangtv.surrounding.apis.Apis;
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.controller.PlaceData;
import com.thangtv.surrounding.ui.helper.ViewHelper;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChooseLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Toolbar toolbar;
    private AutoCompleteTextView mAutocompleteView;
    private ImageView imClear;

    private GoogleMap googleMap;
    private String myLocationName;
    private double myLocationLat;
    private double myLocationLng;
    private String locationName;
    private double locationLat;
    private double locationLng;

    private MapPlaceAutocompleteAdapter mAdapter;

    private Marker myLocationMarker;
    private Marker locationMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        //set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_grey_700_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Set up map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.view_route_map);
        mapFragment.getMapAsync(this);

        setupAutoComplete();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        Location myLocation = PlaceData.getGpsData(this);

        if (myLocation != null) {
            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            myLocationName = PlaceData.getAddressFromLocation(myLocation, this);
            myLocationLat = myLocation.getLatitude();
            myLocationLng = myLocation.getLongitude();
            if (myLocationMarker != null) {
                myLocationMarker.remove();
            }
            myLocationMarker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(myLocationName)
                    .snippet(null)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_location)));

        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocationLat, myLocationLng), 14));

    }

    public void getMyLocation(View view) {

        Location myLocation = PlaceData.getGpsData(this);

        if (myLocation != null) {
            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            myLocationName = PlaceData.getAddressFromLocation(myLocation, this);
            myLocationLat = myLocation.getLatitude();
            myLocationLng = myLocation.getLongitude();
            if (myLocationMarker != null) {
                myLocationMarker.remove();
            }
            myLocationMarker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(myLocationName)
                    .snippet(null)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_location)));


            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocationLat, myLocationLng), 14));

            locationName = myLocationName;
            locationLat = myLocationLat;
            locationLng = myLocationLng;
            if (locationMarker != null) {
                locationMarker.remove();
            }
            locationMarker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(locationName)
                    .snippet(null));

            mAutocompleteView.setText(locationName);

        }


    }

    public void done (View v) {
        Intent intent = new Intent();
        intent.putExtra("locationName", locationName);
        intent.putExtra("locationLat",locationLat);
        intent.putExtra("locationLng", myLocationLng);
        setResult(Const.RC_CHOOSE_LOCATION, intent);
        finish();
    }

    private void setupAutoComplete() {

        mAutocompleteView = (AutoCompleteTextView) findViewById(R.id.autoTv_place);
        // Register a listener that receives callbacks when a suggestion has been selected
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);
        mAutocompleteView.addTextChangedListener(mAutocompleteTextWatcher);
        mAutocompleteView.setOnEditorActionListener(mAutocompleteTextEditor);

        // Set up the adapter that will retrieve suggestions from the Places Geo Data API that cover
        // the entire world.
        mAdapter = new MapPlaceAutocompleteAdapter(this, Apis.mGoogleApiClient, Const.MAP_BOUNDS_NORTH_VIETNAM,
                null, imClear);
        mAutocompleteView.setAdapter(mAdapter);

        imClear = (ImageView) findViewById(R.id.bt_map_toolbar_clear);
        imClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutocompleteView.setText("");
                ViewHelper.hideKeyboard(ChooseLocationActivity.this);
            }
        });


    }

    TextWatcher mAutocompleteTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0) {
                imClear.setImageResource(R.drawable.ic_search_grey_700_24dp);
            } else {
                imClear.setImageResource(R.drawable.ic_clear_grey_700_24dp);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextView.OnEditorActionListener mAutocompleteTextEditor = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.FLAG_EDITOR_ACTION) {
                ViewHelper.hideKeyboard(ChooseLocationActivity.this);
            }
            return false;
        }
    };

    /**
     * Listener that handles selections from suggestions from the AutoCompleteTextView that
     * displays Place suggestions.
     * Gets the place id of the selected item and issues a request to the Places Geo Data API
     * to retrieve more details about the place.
     *
     * @see com.google.android.gms.location.places.GeoDataApi#getPlaceById(com.google.android.gms.common.api.GoogleApiClient,
     * String...)
     */
    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */

            final AutocompletePrediction item = mAdapter.getItem(position);

            locationName = item.getFullText(null).toString();
            String destinationID = item.getPlaceId();
            PlaceData.getPlaceWithId(destinationID, mUpdatePlaceDetailsCallback);
        }
    };


    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);

            locationLat = place.getLatLng().latitude;
            locationLng = place.getLatLng().longitude;

            //clear recent maker, add new maker and move map to that
            if (locationMarker != null) {
                locationMarker.remove();
            }
            locationMarker = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(locationLat, locationLng))
                    .title(locationName));

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locationLat, locationLng), 14));


            // Hide keyboard
            ViewHelper.hideKeyboard(ChooseLocationActivity.this);

            places.release();
        }
    };


}
