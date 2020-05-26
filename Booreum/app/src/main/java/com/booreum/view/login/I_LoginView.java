package com.booreum.view.login;

import android.content.Intent;

public interface I_LoginView {
    public void onLoginResult(Boolean result);
    public void onSocialLoginResult(Intent intent);
    public void onSignUp();
    public void onLoginFacebook();
    public void onLoginKakaoTalk();
    public void onLoginGoogle(Intent intent);
    public void onProgressBarVisibility(int visibility);
    public void onSocialProgressBarVisibility(int visibility);
}
