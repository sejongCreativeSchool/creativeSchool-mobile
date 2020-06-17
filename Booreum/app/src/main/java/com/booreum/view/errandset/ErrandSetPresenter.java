package com.booreum.view.errandset;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.booreum.booreum.R;
import com.booreum.view.errandset.fragment.WhatFragment;

public class ErrandSetPresenter implements I_ErrandSetPresenter{

    private Context context;
    private int categoryNumbering;
    private I_ErrandSetView i_errandSetView;

    private int nowViewNumber = 0;
    static String whatStr, fromStr = "test", toStr, whenStr, pointStr;

    public ErrandSetPresenter(Context context, int categoryNumbering) {
        this.context = context;
        this.categoryNumbering = categoryNumbering;
        i_errandSetView = new ErrandSetActivity();
    }

    @Override
    public void setViewTitle(TextView set_title_tv , ImageView set_title_image,TextView check_title_tv , ImageView check_title_image) {
        String titleText="";
        int resource=0;

        switch (categoryNumbering){
            case 1:
                titleText = "가져다줘";
                resource = R.drawable.icon_bring_detail;
                break;
            case 2:
                titleText = "사다줘";
                resource = R.drawable.icon_buy_detail;
                break;
            case 3:
                titleText = "전달해줘";
                resource = R.drawable.icon_deliver_detail;
                break;
            case 4:
                titleText = "제출해줘";
                resource = R.drawable.icon_submit_detail;
                break;
            case 5:
                titleText = "프린트해줘";
                resource = R.drawable.icon_print_detail;
                break;
            case 6:
                titleText = "같이해줘";
                resource = R.drawable.icon_together_detail;
                break;
            case 7:
                titleText = "대신해줘";
                resource = R.drawable.icon_instead_detail;
                break;
            case 8:
                titleText = "기타";
                resource = R.drawable.icon_etc_detail;
                break;
        }

        set_title_tv.setText(titleText);
        check_title_tv.setText(titleText);
        set_title_image.setImageResource(resource);
        check_title_image.setImageResource(resource);
    }

    @Override
    public void checkFocusEdittext(MotionEvent event, View v) {
        if (v instanceof EditText) {
            Rect outRect = new Rect();
            v.getGlobalVisibleRect(outRect);
            if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void setNextPage(ViewPager viewPager) {
        viewPager.setCurrentItem(getNowpositionNumber_Plus());
    }

    @Override
    public void setPreviousPage(ViewPager viewPager) {
        viewPager.setCurrentItem(getNowpositionNumber_Minus());
    }



    public int getNowpositionNumber_Plus() {
        if(nowViewNumber==0){
            String str = WhatFragment.what.getText().toString();
            if(!str.isEmpty()) {
                //str이 비어있지 않으면
                whatStr = WhatFragment.what.getText().toString();
                return ++nowViewNumber;
            }
        }

        else if (nowViewNumber >= 4) {
            if (!(whatStr == null || fromStr == null || toStr == null || whenStr == null || pointStr == null)) {
                //모두가 널이 아니면
                i_errandSetView.setFrameVisible();
            }
        }

        Toast.makeText(context, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
        return nowViewNumber;
    }

    public int getNowpositionNumber_Minus() {
        return nowViewNumber = --nowViewNumber < 0 ? 0 : nowViewNumber;
    }
}
