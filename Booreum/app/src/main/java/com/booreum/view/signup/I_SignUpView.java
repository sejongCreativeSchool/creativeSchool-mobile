package com.booreum.view.signup;

public interface I_SignUpView {
    void onSuccessSignUp();
    void onFailedSignUp(String message);
    void isPasswordCheckerConfirm();
    void resetView();
    public void onSocialProgressBarVisibility(int visibility);
}
