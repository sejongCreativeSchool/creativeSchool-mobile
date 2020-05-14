package com.booreum.booreum.login;

public interface I_LoginView {
    public void onLoginResult(Boolean result, int code);
    public void onAutoLogin();
    public void onSignIn();
    public void onLoginFacebook();
    public void onLoginKakaoTalk();
    public void onLoginGoogle();
    public void onProgressBarVisibility(int visibility);
}
