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
import com.booreum.view.main.MainActivity;
import com.booreum.view.main.fragment.category.CategoryFragment;

public class CheckErrandActivity extends CustomAppCompatForToolbar implements I_CheckErrandView, View.OnClickListener {

    /**
     * 프레임2번
     */
    private ConstraintLayout parentLayout;
    private TextView checkErrand_titie;
    private ImageView checkErrand_title_image;
    private Button checkErrand_button;
    public static EditText editText;

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
        checkErrand_button.setOnClickListener(this);
    }

    private void initView() {
        parentLayout = (ConstraintLayout)findViewById(R.id.checkErrand_parent);

        Intent intent = getIntent();
        categoryNumber = intent.getExtras().getInt(CategoryFragment.CATEGORY_NUMBERING_KEY);

        i_checkErrandPresenter = new CheckErrandPresenter(categoryNumber, this, total_minute);

        checkErrand_titie= (TextView)findViewById(R.id.errandSet_checkErrand_title_tv);
        checkErrand_title_image = (ImageView)findViewById(R.id.errandSet_checkErrand_title_image);
        checkErrand_button = (Button)findViewById(R.id.checkErrand_button);

        editText = (EditText)findViewById(R.id.errandSet_edit);

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
            case R.id.checkErrand_button:
                resetView();
                i_checkErrandPresenter.setErrandInRetrofit(     );
                break;
        }
    }

    @Override
    public void finishErrandUpload() {
        finish();
    }

    void finishActivity(){
        Intent intent = new Intent(CheckErrandActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}