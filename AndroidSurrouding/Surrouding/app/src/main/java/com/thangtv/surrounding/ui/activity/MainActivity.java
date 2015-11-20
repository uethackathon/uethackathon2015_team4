package com.thangtv.surrounding.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thangtv.surrounding.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if(Var.get(this, "currentUserID")!=null) {
            //send user to MapViewActivity
            Intent intent = new Intent (MainActivity.this, FeedActivity.class);
            startActivity(intent);
        } else {
            //send user to LoginSignupActivity
            Intent intent1 = new Intent (MainActivity.this, LoginSignupActivity.class);
            startActivity(intent1);
        }*/

        Intent intent =new Intent(MainActivity.this, FeedActivity.class);
        startActivity(intent);

    }
}
