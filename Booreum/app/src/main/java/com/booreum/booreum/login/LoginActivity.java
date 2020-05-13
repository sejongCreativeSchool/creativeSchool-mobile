package com.booreum.booreum.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.booreum.booreum.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText login_id, login_pw;
    private Button loginButton, signUpButton, findIdPwButton;
    private CheckBox autoLoginCheckBox;
    private ImageButton login_facebook, login_kakao, login_google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //find View
        login_id = (EditText)findViewById(R.id.login_id);
        login_pw = (EditText)findViewById(R.id.login_pw);
        loginButton = (Button)findViewById(R.id.login_loginButton);
        //signUpButton = (Button)findViewById(R.id.login_signUp);
        //findIdPwButton = (Button)findViewById(R.id.login_findIdPw);
        autoLoginCheckBox = (CheckBox)findViewById(R.id.login_isAutoLoginChecked);
        login_facebook = (ImageButton)findViewById(R.id.login_facebook);
        login_kakao = (ImageButton)findViewById(R.id.login_kakao);
        login_google = (ImageButton)findViewById(R.id.login_google);

        //set Listener
        loginButton.setOnClickListener(this);
        //signUpButton.setOnClickListener(this);
        //findIdPwButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_loginButton :
                break;
            case R.id.login_signUp :
                //break;
            case R.id.login_findIdPw :
                Toast.makeText(getApplicationContext(), "추후 업데이트", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
