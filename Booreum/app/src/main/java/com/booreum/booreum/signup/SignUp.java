package com.booreum.booreum.signup;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.booreum.booreum.CustomAppCompatForToolbar;
import com.booreum.booreum.MyToolBar;
import com.booreum.booreum.R;

public class SignUp extends CustomAppCompatForToolbar {

    private EditText name, id, pw, pw_check, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getHomeAsUpActionBar();
    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.signup_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }
}
