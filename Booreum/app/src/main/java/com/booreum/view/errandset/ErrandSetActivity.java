package com.booreum.view.errandset;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.booreum.R;
import com.booreum.view.main.fragment.category.CategoryFragment;

public class ErrandSetActivity extends CustomAppCompatForToolbar implements I_ErrandSetView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errand_set);

        Intent intent = getIntent();
        int categoryNumbering = intent.getExtras().getInt(CategoryFragment.CATEGORY_NUMBERING_KEY);

        initView();

    }

    private void initView() {
        ActionBar actionBar = getHomeAsUpActionBar();

    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.errandSet_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }
}