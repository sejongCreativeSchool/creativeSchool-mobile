package com.booreum.booreum.view.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.booreum.booreum.constant.PreferenceManager;
import com.booreum.booreum.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements I_LoginPresenter {

    Context context;
    I_LoginView i_loginView;
    User user;
    FirebaseAuth mAuth;

    public LoginPresenter(I_LoginView i_loginView, Context context) {
        this.i_loginView = i_loginView;
        this.mAuth = FirebaseAuth.getInstance(); //객체 초기화
        this.context = context;

        if(getCheckBoxChecked()){
            doAutoLoginCheck();
        }
    }

    @Override
    public void doLogin(String id, String password) {
        mAuth.signInWithEmailAndPassword(id, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Boolean result;
                        if(task.isSuccessful()){
                            // Sign in success, update UI with the signed-in user's information
                            result = true;
                            /**
                             * 통신통해서 유저 정보 가져오기
                             */
                            i_loginView.onLoginResult(result);
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w("LoginPresent", "signInWithEmail:failure", task.getException());
                            result = false;
                            i_loginView.onLoginResult(result);
                            i_loginView.onProgressBarVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    public boolean getCheckBoxChecked() {
        return PreferenceManager.getBoolean(context, PreferenceManager.AUTO_LOGIN);
    }

    @Override
    public void setCheckBoxChecked(Boolean isChecked) {
        PreferenceManager.setBoolean(context, PreferenceManager.AUTO_LOGIN, isChecked);
    }

    @Override
    public void doAutoLoginCheck() {

    }

    @Override
    public void doSignUp() {
        i_loginView.onSignUp();
    }

    @Override
    public void doLoginFacebook() {

    }

    @Override
    public void doLoginKakaoTalk() {

    }

    @Override
    public void doLoginGoogle() {

    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        i_loginView.onProgressBarVisibility(visibility);
    }
}
