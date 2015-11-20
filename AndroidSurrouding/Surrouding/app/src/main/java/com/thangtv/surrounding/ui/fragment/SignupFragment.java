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
import com.thangtv.surrounding.ui.activity.EditProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private EditText editUsername;
    private EditText editPass;
    private EditText editConfirmPass;
    private Button btnSignup;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        editUsername = (EditText) view.findViewById(R.id.edit_username);
        editPass = (EditText) view.findViewById(R.id.edit_password);
        editConfirmPass = (EditText) view.findViewById(R.id.edit_confirm_password);
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

        if (editConfirmPass.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), getString(R.string.you_must_enter_your_password), Toast.LENGTH_SHORT).show();
            editPass.requestFocus();
            return;
        }

        //check username

        //send user to editProfileActivity
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }
}
