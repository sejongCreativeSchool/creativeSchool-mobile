package com.booreum.booreum.view.signup;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpPresenter implements I_SignUpPresenter {

    FirebaseAuth mAuth;
    String email, password;

    public SignUpPresenter(String email, String password) {
        this.email = email;
        this.password = password;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void doSignUp() {

    }
}
