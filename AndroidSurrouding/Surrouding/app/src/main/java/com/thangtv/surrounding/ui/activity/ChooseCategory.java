package com.thangtv.surrounding.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.adapter.ViewPagerAdapter;
import com.thangtv.surrounding.adapter.ViewPagerAdapterIconOnly;
import com.thangtv.surrounding.model.CategoryChild;
import com.thangtv.surrounding.model.CategoryParent;
import com.thangtv.surrounding.ui.fragment.CategoriesListFragment;
import com.thangtv.surrounding.ui.fragment.FeedFragment;

import java.util.ArrayList;
import java.util.List;

public class ChooseCategory extends AppCompatActivity {


    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public List<CategoryParent> categoryParentList;
    public List<CategoryChild> result;
    public static android.support.v4.app.FragmentManager fragmentManager;
    private List<CategoriesListFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        //set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //feed data
        categoryParentList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CategoryParent categoryParent = new CategoryParent();
            categoryParent.setTitle("Parent");
            for (int j = 0; j < 5; j++) {
                categoryParent.getCategoryChildList().add(new CategoryChild("child"));
            }
            categoryParentList.add(categoryParent);
        }

        //set up viewpager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        fragments = new ArrayList<>();
        for (int i = 0; i < categoryParentList.size(); i++) {
            CategoriesListFragment fragment = CategoriesListFragment.newInstance(i);
            fragments.add(fragment);
            adapter.addFragment(fragment, "ABC");
        }

        viewPager.setAdapter(adapter);

        //set up tab layout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_choose_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
