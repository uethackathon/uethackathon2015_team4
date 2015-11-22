package com.thangtv.surrounding.ui.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.thangtv.surrounding.R;
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.model.User;
import com.thangtv.surrounding.network.model.register.PostRegister;
import com.thangtv.surrounding.network.service.ServiceImplements;
import com.thangtv.surrounding.ui.activity.EditProfileActivity;

import java.io.File;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private EditText editEmail;
    private EditText editPass;
    private Button btnSignup;
    public static final String API_URL = "http://project.huynguyenis.me";

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        editEmail = (EditText) view.findViewById(R.id.edit_email);
        editPass = (EditText) view.findViewById(R.id.edit_password);
        btnSignup = (Button) view.findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                signup();
                break;
        }
    }

    private void signup() {

        //validation
        if (editEmail.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), getString(R.string.you_must_enter_your_username), Toast.LENGTH_SHORT).show();
            editEmail.requestFocus();
            return;
        }

        if (editPass.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), getString(R.string.you_must_enter_your_password), Toast.LENGTH_SHORT).show();
            editPass.requestFocus();
            return;
        }

        //check username

        //send user to editProfileActivity
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        User user = new User();

        intent.putExtra(Const.KEY_USER, user);
        startActivityForResult(intent, Const.RC_EDIT_PROFILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Const.RC_EDIT_PROFILE:

                if (resultCode == Activity.RESULT_OK) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ServiceImplements service = retrofit.create(ServiceImplements.class);

                    String email = editEmail.getText().toString();
                    String pass = editPass.getText().toString();
                    String date = Var.calendarToTimestamp(data.getStringExtra(Const.KEY_DOB));
                    String fullname = data.getStringExtra(Const.KEY_NAME);
                    String phone = data.getStringExtra(Const.KEY_PHONE_NUMBER);
                    String career = data.getStringExtra(Const.KEY_CAREER);
                    String gender = data.getStringExtra(Const.KEY_GENDER);
                    String description = data.getStringExtra(Const.KEY_DESCRIPTION);

                    String filePath = data.getStringExtra("pathAvatar");
                    RequestBody requestBodyAvatar = null;
                    if (filePath!=null && filePath.length()>0)
                        requestBodyAvatar = getRequestBody(filePath);
                    Log.d("filePath", filePath);

//                    UserNetwork user = new UserNetwork();


                    Call<PostRegister> call = service.postLogin(email,pass, fullname, date,phone, career, gender, description);
                    call.enqueue(new Callback<PostRegister>() {
                        @Override
                        public void onResponse(Response<PostRegister> response, Retrofit retrofit) {
                            PostRegister postRegister = response.body();
                            Log.d("Tan test register",postRegister.getStatus()+"");
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.d("Tan test register",t.toString()+"");
                        }
                    });
                }

        }
    }
    private RequestBody getRequestBody(String filePath){

//        String description = "upload image to server";
        File file = new File(filePath);

        RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return requestBody;
    }
}
