package com.booreum.view.signup;

import com.booreum.model.User;

public interface I_SignUpPresenter {
    void doSignUp(User user, String pwStr, String pwCheckStr);
}
