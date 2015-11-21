package com.thangtv.surrounding.ui.activity;

import android.content.Intent;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thangtv.surrounding.adapter.ViewPagerAdapter;
import com.thangtv.surrounding.adapter.ViewPagerAdapterIconOnly;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.controller.PlaceData;
import com.thangtv.surrounding.ui.fragment.FeedFragment;
import com.thangtv.surrounding.ui.fragment.MapFragment;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.ui.helper.ViewHelper;

public class FeedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        TabLayout.OnTabSelectedListener,
        View.OnClickListener{

    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MapFragment mapFragment;
    private FeedFragment feedFragment;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FloatingActionButton fab;

    private int fabType;

    public static android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
// test get description from server
//        Toast.makeText(FeedActivity.this, "Descriptions: " + Var.currentUser.getDescription(),Toast.LENGTH_LONG).show();
        //set up fragment manager
        fragmentManager = getSupportFragmentManager();

        //set up viewpager and tab layout
        ViewPagerAdapterIconOnly adapter = new ViewPagerAdapterIconOnly(getSupportFragmentManager());

        mapFragment = new MapFragment();
        feedFragment = new FeedFragment();

        adapter.addFragment(mapFragment, "Map");
        adapter.addFragment(feedFragment, "Feed");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_map_white_highlight_24dp));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_access_time_white_24dp));


        //set up toolbar
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //set up navigation drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.nav_open,
                R.string.nav_close
        );


        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //setup floating action button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundColor(ContextCompat.getColor(this, R.color.color_fab_my_location));
        fabType = 0;
        fab.setOnClickListener(this);


        //add navigation header
        View header = navigationView.inflateHeaderView(R.layout.nav_header);

        //put radius
        mapFragment.radius = 2000;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                switch (fabType) {
                    case 0:
                        //get my location
                        Location myLocation = PlaceData.getGpsData(this);

                        //add marker to my location
                        if (myLocation != null) {
                            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                            mapFragment.myLocationName = PlaceData.getAddressFromLocation(myLocation,this);
                            mapFragment.myLocationLat = myLocation.getLatitude();
                            mapFragment.myLocationLng = myLocation.getLongitude();
                            if (mapFragment.myLocationMarker != null) {
                                mapFragment.myLocationMarker.remove();
                            }

                            if (mapFragment.myLocationMarker!= null) {
                                mapFragment.myLocationMarker.remove();
                            }
                            mapFragment.myLocationMarker = mapFragment.googleMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title(mapFragment.myLocationName)
                                    .snippet(null)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_location)));

                        }

                        //animate camera
                        mapFragment.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapFragment.myLocationLat, mapFragment.myLocationLng), 14));

                        //draw circle
                        if (mapFragment.circle!= null) {
                            mapFragment.circle.remove();
                        }
                        mapFragment.circle = ViewHelper.drawCirle(this, mapFragment.googleMap, new LatLng(mapFragment.myLocationLat, mapFragment.myLocationLng), mapFragment.radius);
                        break;
                    case 1:

                        //start add post activity
                        Intent intent = new Intent(FeedActivity.this, AddPostActivity.class);
                        startActivity(intent);
                        break;
                }
                break;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                return true;
            case R.id.notifications:
                return true;
            case R.id.nearby_feed:
                return true;
            case R.id.settings:
                return true;
            case R.id.log_out:
                return true;

        }
        return false;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        ViewHelper.animateFab(this, fab, tab.getPosition());
        fabType = tab.getPosition();
        if(tab.getPosition()==0) {
            tab.setIcon(getResources().getDrawable(R.drawable.ic_map_white_highlight_24dp));
        } else {
            tab.setIcon(getResources().getDrawable(R.drawable.ic_access_time_white_highlight_24dp));
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if(tab.getPosition()==0) {
            tab.setIcon(getResources().getDrawable(R.drawable.ic_map_white_24dp));
        } else {
            tab.setIcon(getResources().getDrawable(R.drawable.ic_access_time_white_24dp));
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

