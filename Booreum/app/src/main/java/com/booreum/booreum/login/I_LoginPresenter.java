package com.booreum.booreum.login;

public interface I_LoginPresenter {
    void doLogin(String email, String password);
    void doAutoLogin();
    void doSignIn();
    void doLoginFacebook();
    void doLoginKakaoTalk();
    void doLoginGoogle();
    void setProgressBarVisibility(int visibility);
}
