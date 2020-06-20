package com.booreum.view.errandset.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.booreum.booreum.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WhenFragment extends Fragment implements View.OnClickListener {

    private int hour_origin;
    private int minute_origin;

    public static int total_minute;
    private int total_minute_origin;
    Context context;

    private TextView nowTime, requestTime;
    private TimePicker timePicker;
    private Button reset, plus_10, plus_20, plus_30;

    boolean viewTouch = true;

    public WhenFragment(Context context) {
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_when, container, false);

        initView(view);
        getNowTime();

        Log.d("ttt", "h : "+total_minute/60%24);
        Log.d("ttt", "m : "+total_minute%60);
        Log.d("ttt", "t : "+total_minute);

        reset.setOnClickListener(this);
        plus_10.setOnClickListener(this);
        plus_20.setOnClickListener(this);
        plus_30.setOnClickListener(this);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.d("ttt", "22");
                if(viewTouch){
                    total_minute = hourOfDay*60+minute;
                    if(total_minute < hour_origin*60 + minute_origin+10){
                        total_minute = total_minute_origin;
                        view.setHour(total_minute/60%24);
                        view.setMinute(total_minute%60);
                        return;
                    }
                }
                setTimeText();
            }
        });

        viewTouch = false;
        Log.d("ttt", "0001");
        timePicker.setHour(total_minute/60%24);
        Log.d("ttt", "0002");
        timePicker.setMinute(total_minute%60);

        Log.d("ttt", "h : "+total_minute/60%24);
        Log.d("ttt", "m : "+total_minute%60);
        Log.d("ttt", "t : "+total_minute);

        return view;
    }

    private void setTimeText(){
        requestTime.setText(total_minute/60%24+"시 "+total_minute%60+"분");
        Log.d("ttt", "last");
        Log.d("ttt", "h : "+total_minute/60%24);
        Log.d("ttt", "m : "+total_minute%60);
        Log.d("ttt", "t : "+total_minute);
        viewTouch = true;
    }


    private void getNowTime() {
        // 현재시간을 msec 으로 구한다.
        long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat nowHour_format = new SimpleDateFormat("HH");
        SimpleDateFormat nowMinute_format = new SimpleDateFormat("mm");
        // nowDate 변수에 값을 저장한다.
        hour_origin = Integer.valueOf( nowHour_format.format(date));
        minute_origin = Integer.valueOf(nowMinute_format.format(date));

        total_minute = hour_origin*60 + minute_origin;

        nowTime.setText(total_minute/60%24+"시 "+total_minute%60+"분");
        Log.d("ttt", "h : "+total_minute/60%24);
        Log.d("ttt", "m : "+total_minute%60);
        Log.d("ttt", "t : "+total_minute);

        total_minute += 10;
        total_minute_origin = total_minute;
    }

    private void initView(View view) {
        nowTime = (TextView)view.findViewById(R.id.when_nowTime);
        requestTime = (TextView)view.findViewById(R.id.when_requestTime);
        timePicker = (TimePicker)view.findViewById(R.id.when_timepicker);
        timePicker.setIs24HourView(true);
        reset = (Button)view.findViewById(R.id.when_reset);
        plus_10 = (Button)view.findViewById(R.id.when_10);
        plus_20 = (Button)view.findViewById(R.id.when_20);
        plus_30 = (Button)view.findViewById(R.id.when_30);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.when_reset:
                viewTouch = false;
                total_minute = total_minute_origin+10;
                timePicker.setHour(total_minute/60%24);
                timePicker.setMinute(total_minute%60);
                break;
            case R.id.when_10:
                total_minute+=10;
                viewTouch = false;
                timePicker.setHour(total_minute/60%24);
                timePicker.setMinute(total_minute%60);
                break;
            case R.id.when_20:
                total_minute+=20;
                viewTouch = false;
                timePicker.setHour(total_minute/60%24);
                timePicker.setMinute(total_minute%60);
                break;
            case R.id.when_30:
                total_minute+=30;
                viewTouch = false;
                timePicker.setHour(total_minute/60%24);
                timePicker.setMinute(total_minute%60);
                break;
        }
    }
}