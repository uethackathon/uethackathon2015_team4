package com.thangtv.surrounding.ui.activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
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
    private Spinner editGender;
    private ImageView editAvatar;
    private Button buttonChangeAvatar;
    private ProgressDialog progressDialog;

    private static final int REQUEST_CAMERA = 0;
    private static final int SELECT_FILE = 1;
    private static final int PIC_CROP = 2;

    private Uri picUri;


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
        editGender = (Spinner) findViewById(R.id.edit_gender);
        buttonChangeAvatar = (Button) findViewById(R.id.button_change_avatar);

        //Set up button change avatar
        buttonChangeAvatar.setOnClickListener(this);

        //set up date of birth input field
        editDateOfBirth.setFocusable(false);
        editDateOfBirth.setClickable(true);
        editDateOfBirth.setOnClickListener(this);

        //Check user logged in or not
        if (Var.get(this, "currentUserID") != null) {
            editName.setText(Var.get(this, "currentName"));
            editDateOfBirth.setText(Var.get(this, "currentDateOfBirth"));
            editPhone.setText(Var.get(this, "currentPhone"));
            editDescription.setText(Var.get(this, "currentDescription"));

            if (Var.get(this, "currentGender").equals("Male")) {
                editGender.setSelection(0);
            } else {
                editGender.setSelection(1);
            }
            picUri = Uri.parse(Var.get(this,"currentPicUri"));
            editAvatar.setImageURI(picUri);
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
                selectImage();
                break;
            case R.id.edit_date_of_birth:
                showDatePicker();
                break;
        }
    }

    private void selectImage() {

        PopupMenu popupMenu = new PopupMenu(EditProfileActivity.this, buttonChangeAvatar);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_select_image, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.take_picture:

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        //Set picUri points to new file where we save avatar
                        picUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                        try {
                            intent.putExtra("return-data", true);
                            startActivityForResult(intent, REQUEST_CAMERA);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case R.id.choose_from_gallery:

                        Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent1.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent1, "select file"), SELECT_FILE);
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
            if (requestCode == SELECT_FILE) {
                picUri = data.getData();
                try {
                    Intent cropIntent = new Intent("com.android.camera.action.CROP");
                    cropIntent.setDataAndType(picUri, "image/*");
                    cropIntent.putExtra("crop", "true");
                    cropIntent.putExtra("aspectX", 1);
                    cropIntent.putExtra("aspectY", 1);
                    cropIntent.putExtra("outputX", 480);
                    cropIntent.putExtra("outputY", 480);
                    cropIntent.putExtra("return-data", true);
                    startActivityForResult(cropIntent, PIC_CROP);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode == REQUEST_CAMERA) {
                try {
                    Intent cropIntent = new Intent("com.android.camera.action.CROP");
                    cropIntent.setDataAndType(picUri, "image/*");
                    cropIntent.putExtra("crop", "true");
                    cropIntent.putExtra("aspectX", 1);
                    cropIntent.putExtra("aspectY", 1);
                    cropIntent.putExtra("outputX", 480);
                    cropIntent.putExtra("outputY", 480);
                    cropIntent.putExtra("return-data", true);
                    startActivityForResult(cropIntent, PIC_CROP);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode == PIC_CROP) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = extras.getParcelable("data");
                editAvatar.setImageBitmap(bitmap);

                //save avatar to sdcard/surrounding/image

                //get path to external storage (SD card)
                File storageDir = new File(Const.imagePath);

                //create storage directories, if they don't exist
                storageDir.mkdirs();

                try {
                    String filePath = storageDir.toString() + "/" +Var.get(this,"currentUser")+"_"+System.currentTimeMillis()+".png";
                    FileOutputStream fileOutputStream = new FileOutputStream(filePath);

                    BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

                    bos.flush();
                    bos.close();

                    Toast.makeText(EditProfileActivity.this, "Saved " +filePath, Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(EditProfileActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
                    Log.d("TEST_SAVE",e.getMessage());
                }

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

                if (editDescription.getText().toString().trim().equals("")) {
                    Toast.makeText(EditProfileActivity.this, getString(R.string.you_must_tell_something_about_yourself), Toast.LENGTH_SHORT).show();
                    editDescription.requestFocus();
                    return false;
                }

                progressDialog.show();

                //Check if we should signUpUser or updateUser and then send user to MapViewActivity

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
