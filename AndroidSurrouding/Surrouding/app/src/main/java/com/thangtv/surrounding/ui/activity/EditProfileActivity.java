package com.thangtv.surrounding.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.ui.helper.ViewHelper;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.thangtv.surrounding.R;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private Toolbar toolbar;
    private EditText editName;
    private EditText editDateOfBirth;
    private EditText editPhone;
    private EditText editDescription;
    private EditText editCarrer;
    private Spinner editGender;
    private ImageView editAvatar;
    private Button buttonChangeAvatar;
    private ProgressDialog progressDialog;



    private Uri avatarUri;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Connect views
        editName = (EditText) findViewById(R.id.edit_name);
        editDateOfBirth = (EditText) findViewById(R.id.edit_date_of_birth);
        editPhone = (EditText) findViewById(R.id.edit_phone_number);
        editDescription = (EditText) findViewById(R.id.edit_description);
        editAvatar = (ImageView) findViewById(R.id.edit_avatar);
        editCarrer = (EditText) findViewById(R.id.edit_carrer);
        editGender = (Spinner) findViewById(R.id.edit_gender);
        buttonChangeAvatar = (Button) findViewById(R.id.button_change_avatar);


        //Set up button change avatar
        buttonChangeAvatar.setOnClickListener(this);

        //set up date of birth input field
        editDateOfBirth.setFocusable(false);
        editDateOfBirth.setClickable(true);
        editDateOfBirth.setOnClickListener(this);

        //Check user logged in or not
        if (Var.currentUser != null) {
            editName.setText(Var.currentUser.getName());
            editDateOfBirth.setText(Var.currentUser.getDob().get(Calendar.DAY_OF_MONTH)
                    + "/"
                    + Var.currentUser.getDob().get(Calendar.MONTH)
                    + "/"
                    + Var.currentUser.getDob().get(Calendar.YEAR));
            editPhone.setText(Var.currentUser.getPhoneNumber());
            editDescription.setText(Var.currentUser.getDescription());
            editCarrer.setText(Var.currentUser.getCarrer());

            if (Var.currentUser.getGender().equals("Male")) {
                editGender.setSelection(0);
            } else {
                editGender.setSelection(1);
            }

            editAvatar.setImageBitmap(Var.currentUser.getAvatar());
        }

        //Set up gender spinner
        List<String> list = new ArrayList<String>();
        list.add("Male");
        list.add("Female");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editGender.setAdapter(dataAdapter);

        //Set up progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.wait));
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_change_avatar:
                selectAvatar();
                break;

            case R.id.edit_date_of_birth:
                showDatePicker();
                break;
        }
    }


    private void selectAvatar() {

        PopupMenu popupMenu = new PopupMenu(EditProfileActivity.this, buttonChangeAvatar);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_select_image, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.take_picture:

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        //Set picUri points to new file where we save avatar
                        avatarUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, avatarUri);
                        try {
                            intent.putExtra("return-data", true);
                            startActivityForResult(intent, Const.RC_AVATAR_REQUEST_CAMERA);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case R.id.choose_from_gallery:

                        Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent1.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent1, "select file"), Const.RC_AVATAR_SELECT_FILE);
                        break;
                    case R.id.remove_picture:
                        editAvatar.setImageResource(R.drawable.user_no_avatar);
                        /*bitmapAvatar = ((BitmapDrawable) editAvatar.getDrawable()).getBitmap();*/
                        break;
                }
                return true;

            }
        });
        popupMenu.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Const.RC_AVATAR_SELECT_FILE) {
                avatarUri = data.getData();
                try {
                    Intent cropIntent = new Intent("com.android.camera.action.CROP");
                    cropIntent.setDataAndType(avatarUri, "image/*");
                    cropIntent.putExtra("crop", "true");
                    cropIntent.putExtra("aspectX", 1);
                    cropIntent.putExtra("aspectY", 1);
                    cropIntent.putExtra("outputX", 480);
                    cropIntent.putExtra("outputY", 480);
                    cropIntent.putExtra("return-data", true);
                    startActivityForResult(cropIntent, Const.RC_AVATAR_CROP);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode == Const.RC_AVATAR_REQUEST_CAMERA) {
                try {
                    Intent cropIntent = new Intent("com.android.camera.action.CROP");
                    cropIntent.setDataAndType(avatarUri, "image/*");
                    cropIntent.putExtra("crop", "true");
                    cropIntent.putExtra("aspectX", 1);
                    cropIntent.putExtra("aspectY", 1);
                    cropIntent.putExtra("outputX", 480);
                    cropIntent.putExtra("outputY", 480);
                    cropIntent.putExtra("return-data", true);
                    startActivityForResult(cropIntent, Const.RC_AVATAR_CROP);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode == Const.RC_AVATAR_CROP) {
                Bundle extras = data.getExtras();
                bitmap = extras.getParcelable("data");
                editAvatar.setImageBitmap(bitmap);
            }

        }
    }


    private void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );


        dpd.show(getFragmentManager(), this.getResources().getString(R.string.date_of_birth));
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        editDateOfBirth.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.done:
                if (editName.getText().toString().trim().equals("")) {
                    Toast.makeText(EditProfileActivity.this, getString(R.string.please_enter_your_name), Toast.LENGTH_SHORT).show();
                    editName.requestFocus();
                    return false;
                }

                if (editPhone.getText().toString().equals("")) {
                    Toast.makeText(EditProfileActivity.this, getString(R.string.please_enter_your_phone_number), Toast.LENGTH_SHORT).show();
                    editPhone.requestFocus();
                    return false;
                }

                if (editCarrer.getText().toString().trim().equals("")) {
                    Toast.makeText(EditProfileActivity.this, "You must tell about your career", Toast.LENGTH_SHORT).show();
                    editCarrer.requestFocus();
                    return false;
                }

                if (editDescription.getText().toString().trim().equals("")) {
                    Toast.makeText(EditProfileActivity.this, getString(R.string.you_must_tell_something_about_yourself), Toast.LENGTH_SHORT).show();
                    editDescription.requestFocus();
                    return false;
                }


                Intent returnIntent = new Intent();
                returnIntent.putExtra(Const.KEY_NAME, editName.getText().toString());
                returnIntent.putExtra(Const.KEY_DOB, editDateOfBirth.getText().toString());
                if (editGender.getSelectedItemPosition() == 0) {
                    returnIntent.putExtra(Const.KEY_GENDER, "Male");
                } else {
                    returnIntent.putExtra(Const.KEY_GENDER, "Female");
                }

                returnIntent.putExtra(Const.KEY_AVATAR, bitmap);
                returnIntent.putExtra(Const.KEY_CAREER, editCarrer.getText().toString());
                returnIntent.putExtra(Const.KEY_DESCRIPTION, editDescription.getText().toString());


                String filePath =null;
                if (bitmap != null) {
                    Uri uriAvatar = ViewHelper.getImageUri(EditProfileActivity.this, bitmap);
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uriAvatar, filePathColumn, null, null, null);
                    if (cursor.moveToFirst()) {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                        filePath = cursor.getString(column_index);
                    }
                    cursor.close();
                    returnIntent.putExtra("pathAvatar", filePath);
                }else
                    returnIntent.putExtra("pathAvatar","");

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                //Check if we should signUpUser or updateUser and then send user to MapViewActivity

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
