package com.booreum.view.main.fragment.category;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.booreum.booreum.R;
import com.booreum.view.errandset.ErrandSetActivity;
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
        intent = new Intent(context.getApplicationContext(), ErrandSetActivity.class);
        switch (v.getId()){
            case R.id.category_bring:

                intent.putExtra(CategoryFragment.CATEGORY_NUMBERING_KEY, CategoryFragment.CATEGORY_NUMBERING_BRING);
                break;
            case R.id.category_buy:
                intent.putExtra(CategoryFragment.CATEGORY_NUMBERING_KEY, CategoryFragment.CATEGORY_NUMBERING_BUY);
                break;
            case R.id.category_deliver:
                intent.putExtra(CategoryFragment.CATEGORY_NUMBERING_KEY, CategoryFragment.CATEGORY_NUMBERING_DELIEVER);
                break;
            case R.id.category_submit:
                intent.putExtra(CategoryFragment.CATEGORY_NUMBERING_KEY, CategoryFragment.CATEGORY_NUMBERING_SUBMIT);
                break;
            case R.id.category_print:
                intent.putExtra(CategoryFragment.CATEGORY_NUMBERING_KEY, CategoryFragment.CATEGORY_NUMBERING_PRINT);
                break;
            case R.id.category_together:
                intent.putExtra(CategoryFragment.CATEGORY_NUMBERING_KEY, CategoryFragment.CATEGORY_NUMBERING_TOGETHER);
                break;
            case R.id.category_instead:
                intent.putExtra(CategoryFragment.CATEGORY_NUMBERING_KEY, CategoryFragment.CATEGORY_NUMBERING_INSTEAD);
                break;
            case R.id.category_ect:
                intent.putExtra(CategoryFragment.CATEGORY_NUMBERING_KEY, CategoryFragment.CATEGORY_NUMBERING_ECT);
                break;
            default:
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                return;
        }

        i_categoryFrag.onIntentErrand(intent);

    }
}
