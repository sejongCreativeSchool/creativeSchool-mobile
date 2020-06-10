package com.booreum.view.register;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.booreum.R;

public class RegisterActiity extends CustomAppCompatForToolbar implements I_RegisterView {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_actiity);

        ActionBar actionBar = getHomeAsUpActionBar();
        initView();

    }

    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.regist_viewpager);


    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }
}