package com.booreum.view.main;

import android.graphics.Color;
import android.view.View;

public interface I_MainView {
    View createTabView(int resId);
    void setTabTitle(String str);
}
