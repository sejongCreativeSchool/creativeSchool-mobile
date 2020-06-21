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
    public void setErrandInRetrofit(int category,String what, String from, String to, String when, String point) {
        String category_string = getCategoryString(category);
        Log.d("ttt", "category : " + category_string);
        Log.d("ttt", "what : " + what);
        Log.d("ttt", "from : " + from);
        Log.d("ttt", "_id : " +  MainPresenter.user.get_id());

        Errand_ errand_ = new  Errand_(MainPresenter.user.get_id(), category_string, from,to, Integer.valueOf( point), when, what);
        //Errand_(String user, String category, String from, String to, int price, String dueto, String desc)

        GitHubServiceProvider.retrofit.uploadErrand(errand_)
                .enqueue(new Callback<Errand_>() {
                    @Override
                    public void onResponse(Call<Errand_> call, Response<Errand_> response) {
                        if(!response.isSuccessful()) {
                            retrofitFailed(response);
                            Log.d("ttt", "response : " + response.toString());
                            Log.d("ttt", "response : " + response.message());
                        }
                        Toast.makeText(context, "심부름 등록 완료", Toast.LENGTH_SHORT).show();
                        ((Activity)context).finish();
                        //i_checkErrandView.finishErrandUpload();
                    }
                    @Override
                    public void onFailure(Call<Errand_> call, Throwable t) {
                        //retrofitFailed();
                        Log.d("ttt", "response : " + t.getMessage());
                    }
                });
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

            case 7:
                return "대신해줘";

            case 8:
                return  "기타";
            default: return "기타";

        }
    }


}
