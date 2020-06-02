package com.booreum.view.main;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.booreum.R;
import com.booreum.adapter.MainAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends CustomAppCompatForToolbar implements I_MainView, TabLayout.BaseOnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainAdapter mAdapter;
    private I_MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
        initView();
        setListener();
        ActionBar actionBar = getDefaultActionBar();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.main_tapLayout);
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);

        tabLayout.setElevation(10);

        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(R.drawable.ic_launcher_background)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(R.drawable.ic_launcher_background)));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(R.drawable.ic_launcher_background)));

        Log.d("MainActivity_", "count = " + tabLayout.getTabCount());
        mAdapter = new MainAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(mAdapter);

        //init title
        mainPresenter.doTabTitle(viewPager.getCurrentItem());
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
    }

    /**특별히 프레젠터가 할 일이 없기에 바로 작성함*/
    @Override
    public View createTabView(int resId) {
        View tabView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab, null);
        ImageView imageView = (ImageView) tabView.findViewById(R.id.custom_tab_icon);
        imageView.setImageResource(resId);
        return tabView;
    }

    @Override
    public void setTabTitle(String str) {
        setToolbarTitle(str);
    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }


    /**
     * 탭 선택시
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());/**특별히 프레젠터가 할 일이 없기에 바로 작성함*/
        mainPresenter.doTabTitle(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


}
