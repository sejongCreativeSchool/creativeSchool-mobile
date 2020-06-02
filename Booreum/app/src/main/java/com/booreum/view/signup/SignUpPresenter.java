package com.booreum.view.signup;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.booreum.Constant.CheckValid;
import com.booreum.Constant.GitHubService;
import com.booreum.Constant.GitHubServiceProvider;
import com.booreum.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter implements I_SignUpPresenter {

    private FirebaseAuth mAuth;
    private I_SignUpView i_signUpView;
    private Context context;
    static GitHubService retrofit1;

    public SignUpPresenter(I_SignUpView i_signUpView, Context context) {
        this.i_signUpView = i_signUpView;
        this.context = context;
        this.mAuth = FirebaseAuth.getInstance();
        this.retrofit1 = GitHubServiceProvider.providerGithubService();
    }

    @Override
    public void doSignUp(User user, String email, String pwStr, String pwCheckStr) {

        i_signUpView.onSocialProgressBarVisibility(View.VISIBLE);

        if ((!CheckValid.isValidEmail(context, email))
                || (!CheckValid.isValidPassword(context, pwStr))) //이메일, 비밀번호 형식 확인
        {
            i_signUpView.resetView();i_signUpView.onSocialProgressBarVisibility(View.INVISIBLE);
            return;
        }

        if (!CheckValid.isValidNotEmpty(context, user.getName(), user.getPhone(), pwCheckStr)) //빈칸확인
        {
            i_signUpView.resetView();
            i_signUpView.onSocialProgressBarVisibility(View.INVISIBLE);
            return;
        }

        if ((!pwCheckStr.isEmpty()) && (!pwStr.equals(pwCheckStr))) {
            i_signUpView.isPasswordCheckerConfirm();
            i_signUpView.resetView();
            i_signUpView.onSocialProgressBarVisibility(View.INVISIBLE);
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, pwStr)
                .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //success
                            Log.d("SignUpPresenter", "createUserWithEmail:success");
                            i_signUpView.resetView();
                            i_signUpView.onSuccessSignUp();

                            FirebaseUser createUser = mAuth.getCurrentUser();
                            retrofit(email,createUser.getUid(), user);
                        }else{
                            //failed
                            Log.w("SignUpPresenter", "createUserWithEmail:failure", task.getException());
                            i_signUpView.onFailedSignUp(task.getException().getLocalizedMessage());
                        }
                    }
                });
    }

    void retrofit( String email, String accessToken, User user)
    {
        Log.d("SignUpPresenter", "name => " + user.getName());
        Log.d("SignUpPresenter", "phone => " + user.getPhone());

        retrofit1.createUser(user.getName(), accessToken, false, user.getPhone())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful())
                        {
                            Log.d("SignUpPresenter", "retrofit : response body =>  " + response.body());
                            Log.d("SignUpPresenter", "retrofit : response to string =>  " + response.toString());
                            Log.d("SignUpPresenter", "retrofit : response message =>  " + response.message());
                            Log.d("SignUpPresenter", "retrofit : response code =>  " + response.code());
                            Log.d("SignUpPresenter", "retrofit : response error body =>  " + response.errorBody());
                            Log.d("SignUpPresenter", "retrofit : response error headers =>  " + response.headers());
                        }
                        else {
                            Log.d("SignUpPresenter", "retrofit : onResponese but failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("SignUpPresenter", "retrofit : ");
                    }
                });


        GitHubServiceProvider.retrofit.loadUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful())
                {
                    Log.d("SignUpPresenter", "retrofit : response body =>  " + response.body());
                    Log.d("SignUpPresenter", "retrofit : response to string =>  " + response.toString());
                    Log.d("SignUpPresenter", "retrofit : response message =>  " + response.message());
                    Log.d("SignUpPresenter", "retrofit : response code =>  " + response.code());
                    Log.d("SignUpPresenter", "retrofit : response error body =>  " + response.errorBody());
                    Log.d("SignUpPresenter", "retrofit : response error headers =>  " + response.headers());
                }
                else {
                    Log.d("SignUpPresenter", "retrofit : onResponese but failed");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("SignUpPresenter", "retrofit : ");
            }
        });
    }
}
