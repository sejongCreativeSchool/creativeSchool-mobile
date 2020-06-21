package com.booreum.view.errandset;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.booreum.Constant.Building;
import com.booreum.booreum.R;
import com.booreum.view.errandcheck.CheckErrandActivity;
import com.booreum.view.errandset.fragment.FromFragment;
import com.booreum.view.errandset.fragment.PointFragment;
import com.booreum.view.errandset.fragment.ToFragment;
import com.booreum.view.errandset.fragment.WhatFragment;
import com.booreum.view.errandset.fragment.WhenFragment;

public class ErrandSetPresenter implements I_ErrandSetPresenter {

    private Context context;
    private int categoryNumbering;
    private I_ErrandSetView i_errandSetView;

    private int nowViewNumber = 0;
    public static String whatStr, fromStr, toStr, whenStr, pointStr;

    public ErrandSetPresenter(Context context, int categoryNumbering) {
        this.context = context;
        this.categoryNumbering = categoryNumbering;
        i_errandSetView = new ErrandSetActivity();
    }

    @Override
    public void setViewTitle(TextView set_title_tv, ImageView set_title_image) {
        String titleText = "";
        int resource = 0;

        switch (categoryNumbering) {
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
        set_title_image.setImageResource(resource);
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

        String errorMessage = "내용을 입력하세요";

        if (nowViewNumber == 0) {
            String str = WhatFragment.what.getText().toString();
            if (!str.isEmpty()) {
                //str이 비어있지 않으면
                whatStr = str;
                Log.d("ttt","test : "+nowViewNumber);
                return ++nowViewNumber;
            }
        } else if (nowViewNumber == 1) {
            String str = FromFragment.fromWhere;
            String strDetail = FromFragment.fromDetail;
            if (str != null && !str.isEmpty()) { //str이 비어있지 않으면
                if (str.equals(Building.building[Building.building.length - 1]) && strDetail.isEmpty()) { // 선택한 장소가 "기타" 인데 "기타" 상세주소를 입력하지 않은 경우a
                    errorMessage = "기타 장소는 상세주소를 입력해야 합니다.";
                } else {
                    if (str.equals(Building.building[Building.building.length - 1]))
                        fromStr = str + " : " + strDetail;
                    else
                        fromStr = str + " " + strDetail;
                    fromStr.replace("null", "");
                    FromFragment.fromWhere = "";
                    FromFragment.fromDetail = "";
                    FromFragment.collapseAllGroup();
                    Log.d("Errand_", "fromstr : " + fromStr);
                    Log.d("ttt","test : "+nowViewNumber);
                    return ++nowViewNumber;
                }
            } else
                errorMessage = "장소를 선택하세요.";
        } else if (nowViewNumber == 2) {
            String str = ToFragment.toWhere;
            String strDetail = ToFragment.toDetail;
            if (str != null && !str.isEmpty()) { //str이 비어있지 않으면
                if (str.equals(Building.building[Building.building.length - 1]) && strDetail.isEmpty()) { // 선택한 장소가 "기타" 인데 "기타" 상세주소를 입력하지 않은 경우a
                    errorMessage = "기타 장소는 상세주소를 입력해야 합니다.";
                } else {
                    if (str.equals(Building.building[Building.building.length - 1]))
                        toStr = str + " : " + strDetail;
                    else
                        toStr = str + " " + strDetail;
                    toStr.replace("null", "");
                    ToFragment.toWhere = "";
                    ToFragment.toDetail = "";
                    ToFragment.collapseAllGroup();
                    Log.d("Errand_", "fromstr : " + toStr);
                    Log.d("ttt","test : "+nowViewNumber);
                    return ++nowViewNumber;
                }
            } else
                errorMessage = "장소를 선택하세요.";
        } else if (nowViewNumber == 3) {
            int time = WhenFragment.total_minute;
            int hour = time / 60 % 24;
            int minute = time % 60;
            whenStr = hour + "시 " + minute + "분";
            Log.d("ttt","test : "+nowViewNumber);
            return ++nowViewNumber;
        }

        else if (nowViewNumber == 4) {
            String str = PointFragment.point.getText().toString();
            if (!str.isEmpty()) {
                //str이 비어있지 않으면
                if (Integer.valueOf(str) > 500) {
                    pointStr = str;
                    Log.d("ttt","test : "+nowViewNumber);
                    ++nowViewNumber;
                }
                else
                    PointFragment.point.setText("");
            }
        }

        if (nowViewNumber > 4) {
            if (whatStr != null && fromStr != null && toStr != null && whenStr != null && pointStr != null) {
                //모두가 널이 아니면

                Log.d("ttt","test");
                Intent intent = new Intent(context, CheckErrandActivity.class);
                intent.putExtra("what", ErrandSetPresenter.whatStr);
                intent.putExtra("from", ErrandSetPresenter.fromStr);
                intent.putExtra("to", ErrandSetPresenter.toStr);
                intent.putExtra("when", ErrandSetPresenter.whenStr);
                intent.putExtra("point",ErrandSetPresenter. pointStr);
                intent.putExtra("total_minute",WhenFragment.total_minute);
                intent.putExtra("category",categoryNumbering);
                ((Activity)context).startActivity(intent);

                return nowViewNumber;
            }
        }

        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        return nowViewNumber;
    }

    public int getNowpositionNumber_Minus() {
        if (nowViewNumber == 1) {
            FromFragment.fromWhere = "";
            FromFragment.fromDetail = "";
            FromFragment.collapseAllGroup();
        } else if (nowViewNumber == 2) {
            ToFragment.toWhere = "";
            ToFragment.toDetail = "";
            ToFragment.collapseAllGroup();
        }
        return nowViewNumber = --nowViewNumber < 0 ? 0 : nowViewNumber;
    }
}
