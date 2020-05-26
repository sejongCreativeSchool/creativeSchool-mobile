package com.booreum.view.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.booreum.booreum.R;
import com.booreum.constant.CheckValid;
import com.booreum.constant.HideKeyboard;
import com.booreum.view.main.MainActivity;
import com.booreum.view.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, I_LoginView {

    private EditText login_id, login_pw;
    private Button loginButton;
    private TextView signUpButton, findIdPwButton;
    private CheckBox autoLoginCheckBox;
    private ImageButton login_facebook, login_kakao, login_google;
    private ConstraintLayout parentLayout;
    private ProgressBar progressBar;

    private I_LoginPresenter loginPresenter;

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
        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);

        //init View
        login_pw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        login_pw.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //set Listener
        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        findIdPwButton.setOnClickListener(this);
        login_id.setOnFocusChangeListener(this);
        login_pw.setOnFocusChangeListener(this);
        autoLoginCheckBox.setOnClickListener(this);
        login_facebook.setOnClickListener(this);
        login_kakao.setOnClickListener(this);
        login_google.setOnClickListener(this);



        /**
         * This is for clearFocus when you touch not edittextView
         * For Security
         * */
        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                resetView();
                return false;
            }
        });

        //init Presenter
        loginPresenter = new LoginPresenter(this, this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_loginButton :
                HideKeyboard.hideKeyBoard(this, loginButton);
                String id = login_id.getText().toString();
                String pw = login_pw.getText().toString();
                if(CheckValid.isValidEmail(this,id) && CheckValid.isValidPassword(this, pw)) {
                    loginPresenter.setProgressBarVisibility(View.VISIBLE);
                    loginPresenter.doLogin(id , pw);
                }
                break;
            case R.id.login_isAutoLoginChecked :
                loginPresenter.setCheckBoxChecked(autoLoginCheckBox.isChecked());
                break;
            case R.id.login_signUp :
                loginPresenter.doSignUp();
                break;
            case R.id.login_findIdPw :
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_facebook:
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_kakao:
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_google:
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus)
            v.setBackground(getDrawable(R.drawable.border_edittext_blue));
        else
            v.setBackground(getDrawable(R.drawable.border_edittext_default));
    }

    void resetView()
    {
        HideKeyboard.hideKeyBoard(this, login_id);
        HideKeyboard.hideKeyBoard(this, login_pw);
    }



    @Override
    public void onLoginResult(Boolean result) {

        if(result)
        {
            //로그인성공하면
            Log.d("LoginActivity", "로그인성공");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            //로그인 실패시
            Log.d("LoginActivity", "로그인실패");
            Toast.makeText(getApplicationContext(), "아이디/비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFacebook() {

    }

    @Override
    public void onLoginKakaoTalk() {

    }

    @Override
    public void onLoginGoogle() {

    }



    @Override
    public void onProgressBarVisibility(int visibility) {
        resetView();
        progressBar.setVisibility(visibility);
        switch (visibility){
            case View.VISIBLE :
                loginButton.setVisibility(View.INVISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                break;
            case View.GONE : case View.INVISIBLE :
                loginButton.setVisibility(View.VISIBLE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                break;
        }
    }
}
