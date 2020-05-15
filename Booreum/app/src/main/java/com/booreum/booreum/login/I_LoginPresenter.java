package com.booreum.booreum.login;

public interface I_LoginPresenter {
    void doLogin(String id, String password);
    boolean getCheckBoxChecked();
    void setCheckBoxChecked(Boolean isChecked);
    void doAutoLoginCheck();
    void doSignIn();
    void doLoginFacebook();
    void doLoginKakaoTalk();
    void doLoginGoogle();
    void setProgressBarVisibility(int visibility);
}
