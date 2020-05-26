package com.booreum.view.main.fragment.category;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.booreum.booreum.R;
import com.booreum.view.login.LoginActivity;

public class CategoryPresenter implements I_CategoryPresenter {

    private Context context;
    private I_CategoryFrag i_categoryFrag;

    public CategoryPresenter(Context context, I_CategoryFrag i_categoryFrag) {
        this.context = context;
        this.i_categoryFrag = i_categoryFrag;
    }


    @Override
    public void selectErrandIntent(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.category_bring:
                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                Log.d("Category", "enter switch");
                break;
            case R.id.category_buy:
                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                break;
            case R.id.category_deliver:
                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                break;
            case R.id.category_submit:
                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                break;
            case R.id.category_print:
                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                break;
            case R.id.category_together:
                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                break;
            case R.id.category_instead:
                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                break;
            case R.id.category_ect:
                intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                break;
            default:
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                return;
        }

        i_categoryFrag.onIntentErrand(intent);

    }
}
