package com.booreum.booreum.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.booreum.booreum.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private EditText login_id, login_pw;
    private Button loginButton;
    private TextView signUpButton, findIdPwButton;
    private CheckBox autoLoginCheckBox;
    private ImageButton login_facebook, login_kakao, login_google;
    private ConstraintLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //find View
        login_id = (EditText)findViewById(R.id.login_id);
        login_pw = (EditText)findViewById(R.id.login_pw);
        loginButton = (Button)findViewById(R.id.login_loginButton);
        signUpButton = (TextView) findViewById(R.id.login_signUp);
        findIdPwButton = (TextView)findViewById(R.id.login_findIdPw);
        autoLoginCheckBox = (CheckBox)findViewById(R.id.login_isAutoLoginChecked);
        login_facebook = (ImageButton)findViewById(R.id.login_facebook);
        login_kakao = (ImageButton)findViewById(R.id.login_kakao);
        login_google = (ImageButton)findViewById(R.id.login_google);
        parentLayout = (ConstraintLayout)findViewById(R.id.login_parentLayout);

        //set Listener
        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        findIdPwButton.setOnClickListener(this);
        login_id.setOnFocusChangeListener(this);
        login_pw.setOnFocusChangeListener(this);

        /**
         * This is for clearFocus when you touch not edittextView
         * For Security
         * */
        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                login_id.clearFocus();
                login_pw.clearFocus();
                imm.hideSoftInputFromWindow(login_id.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(login_pw.getWindowToken(), 0);
                return false;
            }
        });

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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus)
            v.setBackground(getDrawable(R.drawable.border_edittext_blue));
        else
            v.setBackground(getDrawable(R.drawable.border_edittext));
    }
}
