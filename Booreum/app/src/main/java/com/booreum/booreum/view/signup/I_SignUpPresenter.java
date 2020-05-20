package com.booreum.booreum.view.signup;

import com.booreum.booreum.model.User;

public interface I_SignUpPresenter {
    void doSignUp(User user, String pwStr, String pwCheckStr);
}
