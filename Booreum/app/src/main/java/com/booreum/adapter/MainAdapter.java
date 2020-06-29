package com.booreum.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.booreum.Constant.PreferenceManager;
import com.booreum.view.main.MainPresenter;
import com.booreum.view.main.fragment.category.CategoryFragment;
import com.booreum.view.main.fragment.chat.ChatFragment;
import com.booreum.view.main.fragment.list.ListFragment;
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
        String status = PreferenceManager.getString(context, PreferenceManager.KEY_STATUS_CHANGE);
        switch (position){
            case 0 :
                if(status.equals(PreferenceManager.HELPER) && MainPresenter.user.getHelper()) {
                    ListFragment listFragment = new ListFragment(context);
                    return listFragment;
                }
                else {
                    CategoryFragment categoryFragment = new CategoryFragment();
                    return categoryFragment;
                }
            case 1:
                ChatFragment chatFragment = new ChatFragment(context);
                return chatFragment;
            case 2:
                SettingFragment settingFragment = new SettingFragment(context);
                return settingFragment;
            case 3:
                ListFragment listFragment = new ListFragment(context);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
