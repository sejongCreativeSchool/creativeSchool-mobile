package com.booreum.booreum.constant;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class HideKeyboard {

    public static void hideKeyBoard(Context context, View view)
    {
        view.clearFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
