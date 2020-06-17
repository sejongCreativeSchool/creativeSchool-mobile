package com.booreum.view.errandset.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.booreum.Constant.HideKeyboard;
import com.booreum.booreum.R;
import com.booreum.view.errandset.ErrandSetActivity;
import com.booreum.view.errandset.I_ErrandSetView;


public class WhatFragment extends Fragment {

    LinearLayout parentLayout;
    View view;
    public static EditText what;
    I_ErrandSetView i_errandSetView = new ErrandSetActivity();

    public WhatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_what, container, false);

        parentLayout=(LinearLayout)view.findViewById(R.id.WhatFragment_parentLayout);
        what = (EditText)view.findViewById(R.id.what_editText);

        what.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == event.KEYCODE_ENTER)
                {
                    return true;
                }
                return false;
            }
        });

        return view;
    }

}