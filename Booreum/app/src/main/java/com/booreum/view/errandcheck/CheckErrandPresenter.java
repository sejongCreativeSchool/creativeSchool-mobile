package com.booreum.view.errandcheck;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.booreum.booreum.R;

public class CheckErrandPresenter implements I_CheckErrandPresenter, TimePickerDialog.OnTimeSetListener {

    int categoryNumbering;
    Context context;
    int total_minute;
    I_CheckErrandView i_checkErrandView;

    public CheckErrandPresenter(int categoryNumbering, Context context, int total_minute) {
        this.categoryNumbering = categoryNumbering;
        this.context = context;
        this.total_minute = total_minute;
        i_checkErrandView = new CheckErrandActivity();
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
    public void setTimeDialog() {
        int hour = total_minute/60%24;
        int minute = total_minute%60;

        TimePickerDialog dialog = new TimePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog, this, hour, minute, true);
        dialog.setTitle("대여반납시간");
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String str="" ;

        int total_minute_temp = hourOfDay*60 + minute;
        if(total_minute > total_minute_temp) {
            Toast.makeText(context, "요청시간이 짧거나 다음 날입니다.\n헬퍼와 요청시간을 상의하세요.", Toast.LENGTH_SHORT).show();
            str = "상의";
        }else
            str += + hourOfDay + "시 " + minute + "분";

        total_minute = total_minute_temp;
        i_checkErrandView.setTimeString(str, total_minute_temp);
    }

    @Override
    public void setErrandInRetrofit(String what, String from, String to, String when, String point) {


    }


}
