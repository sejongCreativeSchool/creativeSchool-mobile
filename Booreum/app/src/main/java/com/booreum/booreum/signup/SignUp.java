package com.booreum.booreum.signup;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.booreum.booreum.CustomAppCompatForToolbar;
import com.booreum.booreum.R;

public class SignUp extends CustomAppCompatForToolbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getHomeAsUpActionBar("회원가입");
    }

    @Override
    protected void linkToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
