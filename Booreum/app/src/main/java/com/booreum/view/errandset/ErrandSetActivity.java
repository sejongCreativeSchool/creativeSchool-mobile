package com.booreum.view.errandset;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.booreum.R;
import com.booreum.view.main.fragment.category.CategoryFragment;
import com.pm10.library.CircleIndicator;

public class ErrandSetActivity extends CustomAppCompatForToolbar implements I_ErrandSetView {

    private ViewPager viewPager;
    private CircleIndicator indicator;
    private TextView titie;
    private ImageView title_image;
    private Button button;
    private I_ErrandSetPresenter i_errandSetPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errand_set);

        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        int categoryNumbering = intent.getExtras().getInt(CategoryFragment.CATEGORY_NUMBERING_KEY);
        i_errandSetPresenter = new ErrandSetPresenter(this, categoryNumbering);

        ActionBar actionBar = getHomeAsUpActionBar();

        viewPager = findViewById(R.id.errendSet_viewpager);
        indicator = findViewById(R.id.errendSet_indicator);
        titie = (TextView)findViewById(R.id.errandSet_title);
        title_image = (ImageView)findViewById(R.id.errandSet_image);
        button = (Button)findViewById(R.id.errendSet_button);

        i_errandSetPresenter.setViewTitle();

    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.errandSet_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }
}