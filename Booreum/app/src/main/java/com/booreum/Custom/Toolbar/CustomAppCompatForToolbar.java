package com.booreum.Custom.Toolbar;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.booreum.Constant.PreferenceManager;
import com.booreum.booreum.R;

public abstract class CustomAppCompatForToolbar extends AppCompatActivity {

    protected MyToolBar toolbar;

    protected abstract void linkToolbar();

    protected void setToolbarImage(){
        toolbar.setBackgroundResource(R.drawable.toolbar_red);
    }

    protected ActionBar getDefaultActionBar(){
        linkToolbar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); // for customizing
        actionBar.setDisplayShowTitleEnabled(false);
        if(PreferenceManager.isHelper(this))
        {
           actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.toolbar_red));
        }
        return actionBar;
    }

    protected ActionBar getHomeAsUpActionBar(){
        ActionBar actionBar = getDefaultActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.clear);
        return actionBar;
    }

    protected void setToolbarTitle(String str)
    {
        TextView textView = (TextView)findViewById(R.id.title_tv);
        textView.setText(str);
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
