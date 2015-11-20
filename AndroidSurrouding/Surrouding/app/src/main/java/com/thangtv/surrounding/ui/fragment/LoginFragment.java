package com.thangtv.surrounding.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thangtv.surrounding.R;
import com.thangtv.surrounding.ui.activity.FeedActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    private EditText editUsername;
    private EditText editPass;
    private Button btnLogin;
    private Button btnForgotPass;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        editUsername = (EditText)  view.findViewById(R.id.edit_username);
        editPass= (EditText) view.findViewById(R.id.edit_password);
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

        if(editPass.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), getString(R.string.you_must_enter_your_password), Toast.LENGTH_SHORT).show();
            editPass.requestFocus();
            return;
        }

        Intent intent = new Intent(getActivity(), FeedActivity.class);
        startActivity(intent);


    }

    private void forgotPassword(){

    }

}
