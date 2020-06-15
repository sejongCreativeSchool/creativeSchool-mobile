package com.booreum.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.booreum.view.main.fragment.category.CategoryFragment;
import com.booreum.view.main.fragment.chat.ChatFragment;
import com.booreum.view.main.fragment.setting.SettingFragment;

public class ErrandSetAdapter extends FragmentStatePagerAdapter {

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

            case 1:

            case 2:

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
