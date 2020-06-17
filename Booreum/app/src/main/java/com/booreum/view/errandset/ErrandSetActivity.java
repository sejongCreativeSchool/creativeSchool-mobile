package com.booreum.view.errandset;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.booreum.Custom.NonSwipeViewpager;
import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.adapter.ErrandSetAdapter;
import com.booreum.booreum.R;
import com.booreum.view.main.fragment.category.CategoryFragment;
import com.pm10.library.CircleIndicator;

public class ErrandSetActivity extends CustomAppCompatForToolbar implements I_ErrandSetView, View.OnClickListener {

    private I_ErrandSetPresenter i_errandSetPresenter;
    private int categoryNumbering;
    private int nowViewNumber= 0;

    /** 프레임1번*/
    private ConstraintLayout setErrand;
    private NonSwipeViewpager viewPager;
    private CircleIndicator indicator;
    private TextView setErrand_titie;
    private ImageView setErrand_title_image;
    private Button setErrand_button_next;
    private Button setErrand_button_previous;

    /** 프레임2번*/
    private ConstraintLayout checkErrand;
    private TextView checkErrand_titie;
    private ImageView checkErrand_title_image;
    private Button checkErrand_button;
    private TextView what, from, to, when, point;

    static String whatStr, fromStr="test", toStr, whenStr, pointStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errand_set);

        initView();
        setListener();

    }


    private void initView() {
        Intent intent = getIntent();
        categoryNumbering = intent.getExtras().getInt(CategoryFragment.CATEGORY_NUMBERING_KEY);
        i_errandSetPresenter = new ErrandSetPresenter(this, categoryNumbering);

        ActionBar actionBar = getHomeAsUpActionBar();

        setErrand = (ConstraintLayout)findViewById(R.id.errandSet_setErrandLayout);
        viewPager = (NonSwipeViewpager)findViewById(R.id.errandSet_viewpager);
        indicator = findViewById(R.id.errandSet_indicator);
        setErrand_titie = (TextView)findViewById(R.id.errandSet_title);
        setErrand_title_image = (ImageView)findViewById(R.id.errandSet_image);
        setErrand_button_next = (Button)findViewById(R.id.errandSet_button_next);
        setErrand_button_previous = (Button)findViewById(R.id.errandSet_button_previous);

        checkErrand = (ConstraintLayout)findViewById(R.id.errandSet_CheckErrand_centerView);
        checkErrand_titie = (TextView)findViewById(R.id.errandSet_checkErrand_title_tv);
        checkErrand_title_image = (ImageView)findViewById(R.id.errandSet_checkErrand_title_image);
        checkErrand_button = (Button)findViewById(R.id.errandSet_checkErrand_button);
        what = (TextView)findViewById(R.id.errandSet_checkErrand_what_et);
        from = (TextView)findViewById(R.id.errandSet_checkErrand_wherefrom_et);
        to = (TextView)findViewById(R.id.errandSet_checkErrand_whereto_et);
        when = (TextView)findViewById(R.id.errandSet_checkErrand_when_et);
        point = (TextView)findViewById(R.id.errandSet_checkErrand_point_et);

        i_errandSetPresenter.setViewTitle(setErrand_titie, setErrand_title_image, checkErrand_titie, checkErrand_title_image);
        ErrandSetAdapter mAdapter = new ErrandSetAdapter(getSupportFragmentManager(), this, 5);
        viewPager.setAdapter(mAdapter);

        indicator.setupWithViewPager(viewPager);
    }
    
    private void setListener() {
        setErrand_button_next.setOnClickListener(this);
        setErrand_button_previous.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.errandSet_button_next:
                viewPager.setCurrentItem(getNowpositionNumber_Plus());
                break;
            case R.id.errandSet_button_previous:
                viewPager.setCurrentItem(getNowpositionNumber_Minus());
                break;
        }
    }

    private int getNowpositionNumber_Plus(){
        if(nowViewNumber>=4){
            if(whatStr==null || fromStr==null || toStr==null || whenStr==null || pointStr==null){

            }
            else{
                checkErrand.setVisibility(View.VISIBLE);
                setErrand.setVisibility(View.GONE);
            }
        }
        else{
            nowViewNumber++;
        }
        return nowViewNumber;
    }
    private int getNowpositionNumber_Minus(){
        return nowViewNumber = --nowViewNumber<0? 0 : nowViewNumber;
    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.errandSet_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }
}