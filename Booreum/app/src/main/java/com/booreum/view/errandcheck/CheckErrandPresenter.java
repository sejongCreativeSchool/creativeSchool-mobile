package com.booreum.view.errandcheck;

import android.app.Activity;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.booreum.Constant.GitHubServiceProvider;
import com.booreum.booreum.R;
import com.booreum.model.Errand;
import com.booreum.model.Errand_;
import com.booreum.model.User;
import com.booreum.view.main.MainActivity;
import com.booreum.view.main.MainPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckErrandPresenter implements I_CheckErrandPresenter{

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
                titleText = "0";
                resource = R.drawable.icon_bring_detail;
                break;
            case 2:
                titleText = "1";
                resource = R.drawable.icon_buy_detail;
                break;
            case 3:
                titleText = "2";
                resource = R.drawable.icon_deliver_detail;
                break;
            case 4:
                titleText = "3";
                resource = R.drawable.icon_submit_detail;
                break;
            case 5:
                titleText = "4";
                resource = R.drawable.icon_print_detail;
                break;
            case 6:
                titleText = "5";
                resource = R.drawable.icon_together_detail;
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
    public void setErrandInRetrofit() {
    }

    void retrofitFailed(Response<Errand_> response){
        Toast.makeText(context, "심부름 등록 실패", Toast.LENGTH_SHORT).show();
        Log.d("ttt", "response : " + response.toString());
        Log.d("ttt", "response : " + response.message());
    }


    String getCategoryString(int categoryNumbering){
        switch (categoryNumbering) {
            case 1:
               return  "가져다줘";
            case 2:
                return "사다줘";

            case 3:
                return "전달해줘";

            case 4:
                return  "제출해줘";

            case 5:
                return  "프린트해줘";

            case 6:
                return "같이해줘";

            default: return "기타";

        }
    }


}
