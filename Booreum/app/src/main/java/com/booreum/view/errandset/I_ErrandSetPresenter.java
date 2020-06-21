package com.booreum.view.errandset;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

public interface I_ErrandSetPresenter {
    void setViewTitle(TextView set_title_tv , ImageView set_title_image);
    void setNextPage(ViewPager viewPager);
    void setPreviousPage(ViewPager viewPager);
    void checkFocusEdittext(MotionEvent event, View v);
}
