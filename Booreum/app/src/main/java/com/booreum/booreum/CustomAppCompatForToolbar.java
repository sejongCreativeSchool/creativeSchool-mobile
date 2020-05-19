package com.booreum.booreum;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public abstract class CustomAppCompatForToolbar extends AppCompatActivity {

    protected MyToolBar toolbar;

    protected abstract void linkToolbar();

    protected ActionBar getDefaultActionBar(){
        linkToolbar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); // for customizing
        actionBar.setDisplayShowTitleEnabled(false);
        return actionBar;
    }

    protected ActionBar getHomeAsUpActionBar(){
        ActionBar actionBar = getDefaultActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.clear);
        return actionBar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
