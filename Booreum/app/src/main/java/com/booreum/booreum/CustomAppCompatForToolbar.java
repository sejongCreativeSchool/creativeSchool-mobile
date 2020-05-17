package com.booreum.booreum;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public abstract class CustomAppCompatForToolbar extends AppCompatActivity {
    Toolbar toolbar;

    abstract void linkToolbar();

    ActionBar getDefaultActionBar(){

        linkToolbar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); // for customizing
        //actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("gi");
        return actionBar;
    }

    ActionBar getHomeAsUpActionBar(){
        ActionBar actionBar = getDefaultActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.clear);
        return actionBar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                Toast.makeText(getApplicationContext(), "종료", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
