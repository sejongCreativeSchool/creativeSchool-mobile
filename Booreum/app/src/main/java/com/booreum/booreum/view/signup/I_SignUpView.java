package com.booreum.booreum.view.signup;

public interface I_SignUpView {
    void onSuccessSignUp();
    void onFailedSignUp(String message);
    void isPasswordCheckerConfirm();
    void resetView();
}
