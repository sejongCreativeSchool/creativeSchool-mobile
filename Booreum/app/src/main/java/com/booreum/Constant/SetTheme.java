package com.booreum.Constant;

import android.app.Activity;
import android.util.Log;

import com.booreum.booreum.R;
import com.booreum.view.main.MainActivity;

public final class SetTheme {

    public static void setTheme(Activity activity){
        if(PreferenceManager.isHelper(activity))
        {
            Log.d("Setting", "activity : " + PreferenceManager.getString(activity,PreferenceManager.KEY_STATUS_CHANGE));
            Log.d("Setting", "in if : if");

            activity.setTheme(R.style.LoginTheme_helper);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.helper_red_dark));
        }
        else{
            Log.d("Setting", "activity : " + PreferenceManager.getString(activity,PreferenceManager.KEY_STATUS_CHANGE));
            Log.d("Setting", "in if : else");
            activity.setTheme(R.style.LoginTheme_needer);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.needer_blue_dark));
        }
    }


}
