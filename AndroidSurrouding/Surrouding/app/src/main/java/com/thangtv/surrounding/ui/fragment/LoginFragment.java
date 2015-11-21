package com.thangtv.surrounding.ui.fragment;


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

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.common.Var;
import com.thangtv.surrounding.model.User;
import com.thangtv.surrounding.network.model.login.PostLogin;
import com.thangtv.surrounding.network.service.ServiceImplements;
import com.thangtv.surrounding.ui.activity.FeedActivity;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private EditText editUsername;
    private EditText editPass;
    private Button btnLogin;
    private Button btnForgotPass;
    public static final String API_URL = "http://project.huynguyenis.me";

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editUsername = (EditText) view.findViewById(R.id.edit_email);
        editPass = (EditText) view.findViewById(R.id.edit_password);
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnForgotPass = (Button) view.findViewById(R.id.btn_forgot_password);

        btnLogin.setOnClickListener(this);
        btnForgotPass.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_forgot_password:
                break;
        }
    }

    private void login() {
        if (editUsername.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), getString(R.string.you_must_enter_your_username), Toast.LENGTH_SHORT).show();
            editUsername.requestFocus();
            return;
        }

        if (editPass.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), getString(R.string.you_must_enter_your_password), Toast.LENGTH_SHORT).show();
            editPass.requestFocus();
            return;
        }

        /**
         * check user...
         */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiceImplements service = retrofit.create(ServiceImplements.class);
//        Call<PostLogin> call = service.postLogin("tuantmtb@gmail.com", "thaibinhcity");
        Call<PostLogin> call = service.postLogin(editUsername.getText().toString(), editPass.getText().toString());
        call.enqueue(new Callback<PostLogin>() {
            @Override
            public void onResponse(Response<PostLogin> response, Retrofit retrofit) {
                Var.showToast(getContext(), "status code: " + response.code());
                if (!response.isSuccess()) {
                    try {
                        Var.showToast(getContext(), response.errorBody().string());


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                PostLogin user = response.body();
                if (user == null || user.getStatus() != 1){
                    Var.showToast(getContext(), "Can't connect server !");
                    return;
                }
                int userid = Integer.parseInt(user.getData().getUserid());
                User user_android = new User(Integer.parseInt(user.getData().getUserid()), user.getData().getEmail(), user.getData().getPassword()
                        , user.getData().getFirstName() + " " + user.getData().getLastName(), Var.timestampToCalendar(user.getData().getDate()), user.getData().getGender(), user.getData().getPhone(), Var.getBitmapFromURL(user.getData().getAvatar())
                        , user.getData().getDescription(), user.getData().getCareer()
                );

//                System.out.println("respone info:");
//                Var.showToast(getContext(), "pass" + user.getData().getPassword());
//                Var.showToast(getContext(), "status:" + user.getStatus());


                // add user
                Var.saveObject(getContext(), Const.KEY_USER, user_android);
                Var.currentUser = user_android;
                Var.showToast(getContext(), "Login success!");
                Intent intent = new Intent(getActivity(), FeedActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("", "onFailure");
                Log.d("", t.getMessage());
            }
        });


    }

    private void forgotPassword() {

    }

}
