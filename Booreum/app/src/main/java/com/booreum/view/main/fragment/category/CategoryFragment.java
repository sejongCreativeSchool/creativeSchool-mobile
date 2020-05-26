package com.booreum.view.main.fragment.category;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.booreum.booreum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements I_CategoryFrag, View.OnClickListener {

    final static int MENU_COUNT = 8;
    private View view;
    public final static int imageButtonID[] = {
            R.id.category_bring,
            R.id.category_buy,
            R.id.category_deliver,
            R.id.category_submit,
            R.id.category_print,
            R.id.category_together,
            R.id.category_instead,
            R.id.category_ect
    };
    private ImageButton imageButton[];
    private I_CategoryPresenter i_categoryPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_category, container, false);
        i_categoryPresenter =  new CategoryPresenter(view.getContext(), this);
        initView();

        return view;
    }

    private void initView(){
        imageButton = new ImageButton[MENU_COUNT];
        for(int i=0; i<MENU_COUNT; i++){
            imageButton[i] = view.findViewById(imageButtonID[i]);
            imageButton[i].setOnClickListener(this);
        }
    }

    @Override
    public void onIntentErrand(Intent intent) {
        if(intent != null)
            startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        i_categoryPresenter.selectErrandIntent(v);
    }
}
