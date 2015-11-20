package com.thangtv.surrounding.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.common.Const;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText editDescription;
    private EditText editLocation;
    private EditText editCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        //set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set up edit description
        editDescription = (EditText) findViewById(R.id.edit_description);

        //set up edit location
        editLocation = (EditText) findViewById(R.id.edit_location);
        editLocation.setFocusable(false);
        editLocation.setOnClickListener(this);

        //set up edit category
        editCategory = (EditText) findViewById(R.id.edit_category);
        editCategory.setFocusable(false);
        editCategory.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_location:
                Intent intent = new Intent(AddPostActivity.this, ChooseLocationActivity.class);
                startActivityForResult(intent, Const.RC_CHOOSE_LOCATION);
                break;
            case R.id.edit_category:
                Intent intent1 = new Intent(AddPostActivity.this, ChooseCategory.class);
                startActivityForResult(intent1, Const.RC_CHOOSE_CATEFORY);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Const.RC_CHOOSE_LOCATION:
                break;
            case Const.RC_CHOOSE_CATEFORY:
                break;
        }
    }
}
