package com.booreum.view.login;

public interface I_LoginView {
    public void onLoginResult(Boolean result);
    public void onSignUp();
    public void onLoginFacebook();
    public void onLoginKakaoTalk();
    public void onLoginGoogle();
    public void onProgressBarVisibility(int visibility);
}
