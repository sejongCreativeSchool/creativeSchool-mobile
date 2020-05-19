package com.booreum.booreum.CustomToolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.booreum.booreum.R;

public class MyToolBar extends RelativeLayout {
    private Context context;
    private TextView textView;
    private Toolbar toolbar;

    public MyToolBar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        getAttrs(attrs);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        getAttrs(attrs, defStyleAttr);
    }

    private void init() {
        String inflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(inflaterService);
        View view = layoutInflater.inflate(R.layout.title, MyToolBar.this, false);
        addView(view);
        textView = findViewById(R.id.title_tv);
        textView.setText("Set Title");
        toolbar = findViewById(R.id.title_toolbar);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyToolBar);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyToolBar, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {

        String text_string = typedArray.getString(R.styleable.MyToolBar_title_text);
        textView.setText(text_string);

        typedArray.recycle();
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

}
