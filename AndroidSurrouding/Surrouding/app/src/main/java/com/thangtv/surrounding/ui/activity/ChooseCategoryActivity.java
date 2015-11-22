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
import com.thangtv.surrounding.adapter.PostNearByAdapter;
import com.thangtv.surrounding.adapter.ViewPagerAdapter;
import com.thangtv.surrounding.adapter.ViewPagerAdapterIconOnly;
import com.thangtv.surrounding.apis.IGetNearByPosts;
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.model.CategoryChild;
import com.thangtv.surrounding.model.CategoryParent;
import com.thangtv.surrounding.network.model.postNearBy.PostContainer;
import com.thangtv.surrounding.network.model.subject.Data;
import com.thangtv.surrounding.network.model.subject.GetAllSubject;
import com.thangtv.surrounding.network.service.ServiceImplements;
import com.thangtv.surrounding.ui.fragment.CategoriesListFragment;
import com.thangtv.surrounding.ui.fragment.FeedFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ChooseCategoryActivity extends AppCompatActivity {


    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public List<List<Data>> categories;
    public static android.support.v4.app.FragmentManager fragmentManager;
    private List<CategoriesListFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        //set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //set up viewpager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.URI_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiceImplements service = retrofit.create(ServiceImplements.class);

        Call<GetAllSubject> call = service.getAllSubject();

        call.enqueue(new Callback<GetAllSubject>() {
                         @Override
                         public void onResponse(Response<GetAllSubject> response, Retrofit retrofit) {

                             categories = response.body().getData();
                             fragments = new ArrayList<>();
                             for (int i = 0; i < categories.size(); i++) {
                                 CategoriesListFragment fragment = CategoriesListFragment.newInstance(i);
                                 fragments.add(fragment);
                                 adapter.addFragment(fragment, categories.get(i).get(0).getSubjectGroupName());

                             }

                             viewPager.setAdapter(adapter);

                             //set up tab layout
                             tabLayout = (TabLayout) findViewById(R.id.tabs);
                             tabLayout.setupWithViewPager(viewPager);


                         }

                         @Override
                         public void onFailure(Throwable t) {

                         }
                     }

        );









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
