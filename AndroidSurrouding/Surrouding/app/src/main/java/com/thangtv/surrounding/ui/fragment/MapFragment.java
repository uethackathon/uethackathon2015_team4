package com.thangtv.surrounding.ui.fragment;


import android.location.Location;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thangtv.surrounding.R;
import com.thangtv.surrounding.controller.PlaceData;
import com.thangtv.surrounding.ui.helper.ViewHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {


    public GoogleMap googleMap;

    public String myLocationName;
    public double myLocationLat;
    public double myLocationLng;

    public Marker myLocationMarker;
    public Circle circle;
    public double radius;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //Set up map fragment

        SupportMapFragment supportMapFragment = ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map));
        supportMapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        Location myLocation = PlaceData.getGpsData(getActivity());

        //add marker at mylocation
        if (myLocation != null) {
            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            myLocationName = PlaceData.getAddressFromLocation(myLocation, getActivity());
            myLocationLat = myLocation.getLatitude();
            myLocationLng = myLocation.getLongitude();
            if (myLocationMarker != null) {
                myLocationMarker.remove();
            }
            if (myLocationMarker!= null) {
                myLocationMarker.remove();
            }
            myLocationMarker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(myLocationName)
                    .snippet(null)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_location)));

        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocationLat, myLocationLng), 14));

        //draw circle
        if (circle!= null) {
            circle.remove();
        }
        circle = ViewHelper.drawCirle(getContext(), googleMap, new LatLng(myLocationLat, myLocationLng), radius);
    }
}
