package com.booreum.view.webview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.booreum.Constant.SetTheme;
import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.booreum.R;

public class WebViewActivity extends CustomAppCompatForToolbar implements I_WebViewView {

    WebView webView;
    private WebSettings mWebSettings; //웹뷰세팅
    I_WebViewPresenter i_webViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetTheme.setTheme(this);
        setContentView(R.layout.activity_web_view);

        ActionBar actionBar = getHomeAsUpActionBar();
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String url = intent.getExtras().getString("URL");
        String title = intent.getExtras().getString("TITLE");

        i_webViewPresenter = new WebViewPresenter();
        webView = (WebView)findViewById(R.id.webView_webView);
        mWebSettings = webView.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        mWebSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부

        webView.loadUrl(url);

        setTabTitle(title);
    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.webView_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }

    @Override
    public void setTabTitle(String title) {
        setToolbarTitle(title);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}