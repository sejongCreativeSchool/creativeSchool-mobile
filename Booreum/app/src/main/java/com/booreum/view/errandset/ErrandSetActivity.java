package com.booreum.view.errandset;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.booreum.Constant.HideKeyboard;
import com.booreum.Custom.NonSwipeViewpager;
import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.adapter.ErrandSetAdapter;
import com.booreum.booreum.R;
import com.booreum.view.errandcheck.CheckErrandActivity;
import com.booreum.view.errandset.fragment.WhatFragment;
import com.booreum.view.main.fragment.category.CategoryFragment;
import com.pm10.library.CircleIndicator;

public class ErrandSetActivity extends CustomAppCompatForToolbar implements I_ErrandSetView, View.OnClickListener {

    private I_ErrandSetPresenter i_errandSetPresenter;
    private int categoryNumbering;
    private ConstraintLayout parentLayout;
    //private FrameLayout frameLayout;

    /**
     * 프레임1번
     */
    private ConstraintLayout setErrand;
    private NonSwipeViewpager viewPager;
    private CircleIndicator indicator;
    private TextView setErrand_titie;
    private ImageView setErrand_title_image;
    private Button setErrand_button_next;
    private Button setErrand_button_previous;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errand_set);
        setErrand = (ConstraintLayout) findViewById(R.id.errandSet_setErrandLayout);
        initView();
        setListener();
    }


    private void initView() {
        parentLayout = (ConstraintLayout) findViewById(R.id.errandSet_parentLayout);

        Intent intent = getIntent();
        categoryNumbering = intent.getExtras().getInt(CategoryFragment.CATEGORY_NUMBERING_KEY);
        i_errandSetPresenter = new ErrandSetPresenter(this, categoryNumbering);

        ActionBar actionBar = getHomeAsUpActionBar();


        viewPager = (NonSwipeViewpager) findViewById(R.id.errandSet_viewpager);
        indicator = findViewById(R.id.errandSet_indicator);
        setErrand_titie = (TextView) findViewById(R.id.errandSet_title);
        setErrand_title_image = (ImageView) findViewById(R.id.errandSet_image);
        setErrand_button_next = (Button) findViewById(R.id.errandSet_button_next);
        setErrand_button_previous = (Button) findViewById(R.id.errandSet_button_previous);


        i_errandSetPresenter.setViewTitle(setErrand_titie, setErrand_title_image);
        ErrandSetAdapter mAdapter = new ErrandSetAdapter(getSupportFragmentManager(), this, 5);
        viewPager.setAdapter(mAdapter);

        indicator.setupWithViewPager(viewPager);
    }

    private void setListener() {
        setErrand_button_next.setOnClickListener(this);
        setErrand_button_previous.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.errandSet_button_next:
                i_errandSetPresenter.setNextPage(viewPager);
                break;
            case R.id.errandSet_button_previous:
                i_errandSetPresenter.setPreviousPage(viewPager);
                break;
        }
    }


    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.errandSet_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            View v = getCurrentFocus();
            i_errandSetPresenter.checkFocusEdittext(event, v);
        }
        return super.dispatchTouchEvent(event);
    }

}