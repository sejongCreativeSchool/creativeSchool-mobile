package com.booreum.booreum.view.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.booreum.booreum.CustomToolbar.CustomAppCompatForToolbar;
import com.booreum.booreum.CustomToolbar.MyToolBar;
import com.booreum.booreum.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends CustomAppCompatForToolbar implements I_MainView{

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.main_tapLayout);
    }


    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }
}
