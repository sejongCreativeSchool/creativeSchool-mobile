package com.booreum.booreum.view.signup;

import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.booreum.booreum.CustomToolbar.CustomAppCompatForToolbar;
import com.booreum.booreum.R;
import com.booreum.booreum.constant.CheckValid;
import com.booreum.booreum.constant.HideKeyboard;
import com.booreum.booreum.constant.SoftKeyboard;

public class SignUpActivity extends CustomAppCompatForToolbar implements I_SignUpView, View.OnClickListener, View.OnFocusChangeListener {

    private EditText name, id, pw, pw_check, email;
    private ConstraintLayout parentLayout;
    private Button button;
    private SoftKeyboard softKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();
        setViewListner();

        InputMethodManager controlManager = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        softKeyboard = new SoftKeyboard(parentLayout, controlManager);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {
            @Override
            public void onSoftKeyboardHide() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplication(), "키보드 내려", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSoftKeyboardShow() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplication(), "키보드 올라감", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("SignUpActivity_", "onBackPressed");
    }

    private void initView() {
        getHomeAsUpActionBar(); //for Toolbar
        name = (EditText) findViewById(R.id.signup_name_et);
        id = (EditText) findViewById(R.id.signup_id_et);
        pw = (EditText) findViewById(R.id.signup_pw_et);
        pw_check = (EditText) findViewById(R.id.signup_pw_check_et);
        email = (EditText) findViewById(R.id.signup_email_et);
        parentLayout = (ConstraintLayout) findViewById(R.id.signup_parentLayout);
        button = (Button) findViewById(R.id.signup_button);
    }

    private void setViewListner() {
        name.setOnFocusChangeListener(this);
        id.setOnFocusChangeListener(this);
        pw.setOnFocusChangeListener(this);
        pw_check.setOnFocusChangeListener(this);
        email.setOnFocusChangeListener(this);

        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    Log.d("SignUpActivity_", "Down or MOVE");
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    resetView();
                    Log.d("SignUpActivity_", "up");
                    return false;
                }

                return true;
            }
        });

        button.setOnClickListener(this);
    }

    private void resetView() {
        HideKeyboard.hideKeyBoard(this, name);
        HideKeyboard.hideKeyBoard(this, id);
        HideKeyboard.hideKeyBoard(this, pw);
        HideKeyboard.hideKeyBoard(this, pw_check);
        HideKeyboard.hideKeyBoard(this, email);

    }

    @Override
    public void onClick(View v) {
        if ((!CheckValid.isValidEmail(this, id))
                || (!CheckValid.isValidPassword(this, pw))) //이메일, 비밀번호 형식 확인
            return;
        if (!CheckValid.isValidNotEmpty(this, name, email)) //빈칸확인
            return;

    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.signup_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }

    @Override
    public void onSignUp() {

    }

    @Override
    public Boolean isPasswordCheckerConfirm() {
        return null;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            v.setBackground(getDrawable(R.drawable.border_edittext_blue));
        else
            v.setBackground(getDrawable(R.drawable.border_edittext_default_signup));
    }

}
