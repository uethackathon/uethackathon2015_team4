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
import com.thangtv.surrounding.common.Const;
import com.thangtv.surrounding.model.User;
import com.thangtv.surrounding.ui.activity.EditProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private EditText editEmail;
    private EditText editPass;
    private Button btnSignup;

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

                //sign up user



        }
    }
}
