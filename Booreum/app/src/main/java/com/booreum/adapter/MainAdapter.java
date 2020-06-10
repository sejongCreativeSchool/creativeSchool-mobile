package com.booreum.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.booreum.view.main.fragment.category.CategoryFragment;
import com.booreum.view.main.fragment.chat.ChatFragment;
import com.booreum.view.main.fragment.setting.SettingFragment;

public class MainAdapter extends FragmentStatePagerAdapter {

    private int pageCount;
    private Context context;

    public MainAdapter(FragmentManager fm, Context context ,int pageCount) {
        super(fm);
        this.pageCount = pageCount;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                CategoryFragment categoryFragment = new CategoryFragment();
                return categoryFragment;
            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            case 2:
                SettingFragment settingFragment = new SettingFragment(context);
                return settingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
