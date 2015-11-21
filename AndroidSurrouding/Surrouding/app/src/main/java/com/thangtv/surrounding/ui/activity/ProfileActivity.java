package com.thangtv.surrounding.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.model.User;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private User user;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView header;
    private TextView name;
    private TextView dateOfBirth;
    private TextView gender;
    private TextView phone;
    private TextView email;
    private TextView description;
    private FloatingActionButton fab;

    @Override
    protected void onResume() {
        super.onResume();
        header.setImageBitmap(user.getAvatar());
        name.setText(user.getName());
        dateOfBirth.setText(user.getDob().get(Calendar.DAY_OF_MONTH)
                + "/"
                + user.getDob().get(Calendar.MONTH)
                + "/"
                + user.getDob().get(Calendar.YEAR));

        gender.setText(user.getGender());
        phone.setText(user.getPhoneNumber());
        email.setText(user.getEmail());
        description.setText(user.getDescription());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //connect views
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        header = (ImageView) findViewById(R.id.header);
        name = (TextView) findViewById(R.id.name);
        gender = (TextView) findViewById(R.id.gender);
        dateOfBirth = (TextView) findViewById(R.id.date_of_birth);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.email);
        description = (TextView) findViewById(R.id.description);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);

        //set up toolbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //get intent
        final Intent intent = getIntent();

        //check user
        int userID = intent.getIntExtra(Const.KEY_USER_ID, 0);
        if (Var.currentUser.getId() == userID) {
            user = Var.currentUser;
            fab.setImageResource(R.drawable.ic_mode_edit_white_36dp);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(ProfileActivity.this, EditProfileActivity.class);
                    intent1.putExtra("user", user);
                    startActivityForResult(intent1, Const.RC_EDIT_PROFILE);
                }
            });
        } else {

            //get user from database

            fab.setImageResource(R.drawable.ic_add_white_36dp);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Const.RC_EDIT_PROFILE:
                if (resultCode == Activity.RESULT_OK) {
                    user = (User) data.getSerializableExtra(Const.KEY_USER);
                }
        }
    }
}
