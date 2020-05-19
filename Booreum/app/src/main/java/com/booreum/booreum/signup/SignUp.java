package com.booreum.booreum.signup;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.booreum.booreum.CustomAppCompatForToolbar;
import com.booreum.booreum.MyToolBar;
import com.booreum.booreum.R;

public class SignUp extends AppCompatActivity {

    MyToolBar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolBar = (MyToolBar)findViewById(R.id.signup_toolbar);
    }

}
