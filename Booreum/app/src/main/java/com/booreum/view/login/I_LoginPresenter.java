package com.booreum.view.login;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface I_LoginPresenter {
    void doLogin(String id, String password);
    boolean getCheckBoxChecked();
    void setCheckBoxChecked(Boolean isChecked);
    void doAutoLoginCheck();
    void doSignUp();
    void doLoginFacebook();
    void doLoginKakaoTalk();
    void doLoginGoogle();
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    void resultLogin(int sns, GoogleSignInAccount account);
    void setProgressBarVisibility(int visibility);
}
