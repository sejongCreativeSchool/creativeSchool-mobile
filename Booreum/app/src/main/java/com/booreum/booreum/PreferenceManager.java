package com.booreum.booreum;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preference를 쉽게 쓰기 위한 클래스
 */

public class PreferenceManager {
    public final static String PREF_NAME = "booreum";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;

    public final static String AUTO_LOGIN = "autoLogin";

    //
    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setBoolean(Context context, String key, Boolean value)
    {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static Boolean getBoolean(Context context, String key)
    {
        SharedPreferences prefs = getPreferences(context);
        boolean value = prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);
        return value;
    }


}
