package com.booreum.view.errandcheck;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

interface I_CheckErrandPresenter {
    void setViewTitle(TextView set_title_tv , ImageView set_title_image);
    void checkFocusEdittext(MotionEvent event, View v);
    void setTimeDialog();
    void setErrandInRetrofit(String what, String from, String to, String when, String point);
}
