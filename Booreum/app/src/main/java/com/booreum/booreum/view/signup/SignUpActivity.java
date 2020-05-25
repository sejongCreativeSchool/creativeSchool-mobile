package com.booreum.booreum.view.signup;

import android.graphics.Point;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.booreum.booreum.CustomToolbar.CustomAppCompatForToolbar;
import com.booreum.booreum.R;
import com.booreum.booreum.constant.CheckValid;
import com.booreum.booreum.constant.HideKeyboard;
import com.booreum.booreum.model.User;

public class SignUpActivity extends CustomAppCompatForToolbar implements I_SignUpView, View.OnClickListener, View.OnFocusChangeListener, View.OnTouchListener {

    private EditText name, id, pw, pw_check, phone;
    private ConstraintLayout parentLayout;
    private Button button;
    private I_SignUpPresenter i_signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();
        setViewListener();
        i_signUpPresenter = new SignUpPresenter(this, this);
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
        phone = (EditText) findViewById(R.id.signup_phone_et);
        parentLayout = (ConstraintLayout) findViewById(R.id.signup_parentLayout);
        button = (Button) findViewById(R.id.signup_button);

        //init View
        pw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        pw_check.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pw_check.setTransformationMethod(PasswordTransformationMethod.getInstance());
        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    private void setViewListener() {
        name.setOnFocusChangeListener(this);
        id.setOnFocusChangeListener(this);
        pw.setOnFocusChangeListener(this);
        pw_check.setOnFocusChangeListener(this);
        phone.setOnFocusChangeListener(this);

        parentLayout.setOnTouchListener(this);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_button:
                User user = new User(id.getText().toString()
                        ,name.getText().toString(),phone.getText().toString());
                String pwStr = pw.getText().toString();
                String pwCheckStr = pw_check.getText().toString();
                i_signUpPresenter.doSignUp(user, pwStr, pwCheckStr);
                break;
            default:

        }

    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.signup_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }

    @Override
    public void onSuccessSignUp() {
        Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailedSignUp(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isPasswordCheckerConfirm() {
        resetView();
        pw_check.setBackground(getDrawable(R.drawable.border_edittext_red));
        Toast.makeText(getApplicationContext(), "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void resetView() {
        View v = getCurrentFocus();
        if(v!=null){
            v.clearFocus();
            HideKeyboard.hideKeyBoard(this, v);
        }
        //HideKeyboard.hideKeyBoard(this, name);
        //HideKeyboard.hideKeyBoard(this, id);
        //HideKeyboard.hideKeyBoard(this, pw);
        //HideKeyboard.hideKeyBoard(this, pw_check);
        //HideKeyboard.hideKeyBoard(this, phone);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            default:
                if (hasFocus)
                    v.setBackground(getDrawable(R.drawable.border_edittext_blue));
                else
                    v.setBackground(getDrawable(R.drawable.border_edittext_default_signup));
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if(v.getId() == parentLayout.getId())
            {
                resetView();
                Log.d("SignUpActivity_", "up in v");
                return false;
            }
            Log.d("SignUpActivity_", "up");
            return false;
        }
        return true;
    }

}
