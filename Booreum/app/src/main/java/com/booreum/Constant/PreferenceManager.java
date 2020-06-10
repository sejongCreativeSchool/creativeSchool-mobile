package com.booreum.Constant;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preference를 쉽게 쓰기 위한 클래스
 */

public class PreferenceManager {
    public final static String PREF_NAME = "booreum";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final String DEFAULT_VALUE_STRING = "needer";

    public final static String KEY_AUTO_LOGIN = "autoLogin";

    public final static String KEY_STATUS_CHANGE = "status_change";
    public final static String HELPER = "helper";
    public final static String NEEDER = "needer";
    //
    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isHelper(Context context){
        return PreferenceManager.getString(context,PreferenceManager.KEY_STATUS_CHANGE)
                .equals(PreferenceManager.HELPER);
    }

    public static void setString(Context context, String key, String value)
    {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String  getString(Context context, String key)
    {
        SharedPreferences prefs = getPreferences(context);
        String value = prefs.getString(key, DEFAULT_VALUE_STRING);
        return value;
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
