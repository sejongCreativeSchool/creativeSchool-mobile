package com.booreum.booreum.signup;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import com.booreum.booreum.CustomAppCompatForToolbar;
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        View cView = getCurrentFocus();
        if(cView != null)
        {
            Rect rect = new Rect();
            cView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(cView.getWindowToken(), 0);
                cView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
