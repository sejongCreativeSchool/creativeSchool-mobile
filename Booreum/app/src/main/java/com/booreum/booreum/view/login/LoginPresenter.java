package com.booreum.booreum.view.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.booreum.booreum.R;
import com.booreum.booreum.constant.GitHubService;
import com.booreum.booreum.constant.GitHubServiceProvider;
import com.booreum.booreum.constant.PreferenceManager;
import com.booreum.booreum.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter implements I_LoginPresenter {

    Context context;
    I_LoginView i_loginView;
    User user;
    FirebaseAuth mAuth;
    GitHubService retrofit;

    public LoginPresenter(I_LoginView i_loginView, Context context) {
        this.i_loginView = i_loginView;
        this.mAuth = FirebaseAuth.getInstance(); //객체 초기화
        this.context = context;
        this.retrofit = GitHubServiceProvider.providerGithubService();

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
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            result = true;
                            /**
                             * 통신통해서 유저 정보 가져오기
                             */
                            i_loginView.onLoginResult(result);
                            retrofit();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LoginPresent", "signInWithEmail:failure", task.getException());
                            result = false;
                            i_loginView.onLoginResult(result);
                            i_loginView.onProgressBarVisibility(View.GONE);
                        }
                    }
                });

    }

    void retrofit()
    {
        Log.d("LoginPresent", "do test retrofit init");

        retrofit.listErrand().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("LoginPresent", "onResponse body= " + response.body());
                Log.d("LoginPresent", "onResponse message= " + response.message());
                Log.d("LoginPresent", "onResponse tostring= " + response.toString());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("LoginPresent", "onFailure = " + t.getMessage());
            }
        });

        Log.d("LoginPresent", "d");
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
