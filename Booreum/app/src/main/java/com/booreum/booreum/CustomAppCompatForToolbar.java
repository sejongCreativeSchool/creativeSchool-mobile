package com.booreum.booreum;

import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public abstract class CustomAppCompatForToolbar extends AppCompatActivity {
    protected Toolbar toolbar;

    protected abstract void linkToolbar();

    protected ActionBar getDefaultActionBar(String title){

        linkToolbar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); // for customizing
        //actionBar.setDisplayShowTitleEnabled(false);
        //actionBar.setTitle(title);
        //actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>" + title + "</font>"));
        //actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //actionBar.setCustomView(R.layout.title);
        TextView textView = (TextView)findViewById(R.id.title_);
        textView.setText(title);
        return actionBar;
    }

    protected ActionBar getHomeAsUpActionBar(String title){
        ActionBar actionBar = getDefaultActionBar(title);
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
