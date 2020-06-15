package com.booreum.view.errandset;

import android.content.Context;

public class ErrandSetPresenter implements I_ErrandSetPresenter{

    private Context context;
    private int categoryNumbering;

    public ErrandSetPresenter(Context context, int categoryNumbering) {
        this.context = context;
        this.categoryNumbering = categoryNumbering;
    }

    @Override
    public void setViewTitle() {
        switch (categoryNumbering){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
        }
    }
}
