package com.booreum.view.errandcheck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.booreum.Constant.HideKeyboard;
import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.Custom.Toolbar.MyToolBar;
import com.booreum.booreum.R;
import com.booreum.view.errandset.ErrandSetActivity;
import com.booreum.view.main.MainActivity;

public class CheckErrandActivity extends CustomAppCompatForToolbar implements I_CheckErrandView, View.OnClickListener {

    /**
     * 프레임2번
     */
    private ConstraintLayout parentLayout;
    private TextView checkErrand_titie;
    private ImageView checkErrand_title_image;
    private Button checkErrand_button;
    public static EditText what, from, to, when, point;
    private String whatStr, fromStr, toStr, whenStr, pointStr;

    int total_minute;

    int categoryNumber;
    I_CheckErrandPresenter i_checkErrandPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_errand);

        initView();
        setListener();
        ActionBar actionBar = getHomeAsUpActionBar();

    }

    private void setListener() {
        when.setOnClickListener(this);
        checkErrand_button.setOnClickListener(this);
    }

    private void initView() {
        parentLayout = (ConstraintLayout)findViewById(R.id.checkErrand_parent);

        Intent intent = getIntent();
        categoryNumber = intent.getExtras().getInt("category");
        whatStr = intent.getExtras().getString("what");
        toStr = intent.getExtras().getString("to");
        fromStr = intent.getExtras().getString("from");
        whenStr = intent.getExtras().getString("when");
        pointStr = intent.getExtras().getString("point");

        total_minute = intent.getExtras().getInt("total_minute");

        i_checkErrandPresenter = new CheckErrandPresenter(categoryNumber, this, total_minute);

        checkErrand_titie= (TextView)findViewById(R.id.errandSet_checkErrand_title_tv);
        checkErrand_title_image = (ImageView)findViewById(R.id.errandSet_checkErrand_title_image);
        checkErrand_button = (Button)findViewById(R.id.checkErrand_button);
        what = (EditText) findViewById(R.id.errandSet_checkErrand_what_et); what.setText(whatStr);
        from = (EditText)findViewById(R.id.errandSet_checkErrand_wherefrom_et); from.setText(fromStr);
        to = (EditText)findViewById(R.id.errandSet_checkErrand_whereto_et); to.setText(toStr);
        when = (EditText)findViewById(R.id.errandSet_checkErrand_when_et); when.setText(whenStr);
        point = (EditText)findViewById(R.id.errandSet_checkErrand_point_et); point.setText(pointStr);

        i_checkErrandPresenter.setViewTitle(checkErrand_titie, checkErrand_title_image);

    }

    @Override
    protected void linkToolbar() {
        toolbar = (MyToolBar)findViewById(R.id.checkErrand_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }

    public void resetView() {
        View v = getCurrentFocus();
        if(v!=null){
            v.clearFocus();
            HideKeyboard.hideKeyBoard(this, v);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            View v = getCurrentFocus();
            i_checkErrandPresenter.checkFocusEdittext(event, v);
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.errandSet_checkErrand_when_et:
                i_checkErrandPresenter.setTimeDialog();
                break;

            case R.id.checkErrand_button:
                resetView();
                i_checkErrandPresenter.setErrandInRetrofit(categoryNumber,
                        what.getText().toString(), from.getText().toString(),
                        to.getText().toString(), when.getText().toString(), point.getText().toString());
                break;
        }
    }

    @Override
    public void setTimeString(String str, int total) {
        when.setText(str);
        total_minute = total;
    }

    @Override
    public void finishErrandUpload() {
        Log.d("ttt", "please turn off0");
        Log.d("ttt", "please turn off1");
        //finishActivity();
        Log.d("ttt", "please turn off2");
        finish();
    }

    void finishActivity(){
        Intent intent = new Intent(CheckErrandActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}