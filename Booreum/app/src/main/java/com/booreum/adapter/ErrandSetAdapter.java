package com.booreum.adapter;

import android.content.Context;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.booreum.view.errandset.fragment.FromFragment;
import com.booreum.view.errandset.fragment.PointFragment;
import com.booreum.view.errandset.fragment.ToFragment;
import com.booreum.view.errandset.fragment.WhatFragment;
import com.booreum.view.errandset.fragment.WhenFragment;
import com.booreum.view.main.fragment.category.CategoryFragment;
import com.booreum.view.main.fragment.chat.ChatFragment;
import com.booreum.view.main.fragment.setting.SettingFragment;

public class ErrandSetAdapter extends FragmentStatePagerAdapter{

    private int pageCount;
    private Context context;

    public ErrandSetAdapter(FragmentManager fm, Context context ,int pageCount) {
        super(fm);
        this.pageCount = pageCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                WhatFragment whatFragment = new WhatFragment();
                return whatFragment;
            case 1:
                FromFragment fromFragment = new FromFragment(context);
                return fromFragment;
            case 2:
                ToFragment toFragment = new ToFragment();
                return toFragment;
            case 3:
                WhenFragment whenFragment = new WhenFragment();
                return whenFragment;
            case 4:
                PointFragment pointFragment = new PointFragment();
                return pointFragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return pageCount;
    }
}
