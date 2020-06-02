package com.booreum.view.signup;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

    public SignUpPresenter(I_SignUpView i_signUpView, Context context) {
        this.i_signUpView = i_signUpView;
        this.context = context;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void doSignUp(User user, String email, String pwStr, String pwCheckStr) {

        i_signUpView.onProgressBarVisibility(View.VISIBLE);
        i_signUpView.resetView();

        if ((!CheckValid.isValidEmail(context, email))
                || (!CheckValid.isValidPassword(context, pwStr))) //이메일, 비밀번호 형식 확인
        {
            i_signUpView.resetView();i_signUpView.onProgressBarVisibility(View.INVISIBLE);
            return;
        }

        if (!CheckValid.isValidNotEmpty(context, user.getName(), user.getPhone(), pwCheckStr)) //빈칸확인
        {
            i_signUpView.resetView();
            i_signUpView.onProgressBarVisibility(View.INVISIBLE);
            return;
        }

        if ((!pwCheckStr.isEmpty()) && (!pwStr.equals(pwCheckStr))) {
            i_signUpView.isPasswordCheckerConfirm();
            i_signUpView.resetView();
            i_signUpView.onProgressBarVisibility(View.INVISIBLE);
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, pwStr)
                .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //success
                            Log.d("SignUpPresenter", "createUserWithEmail:success");

                            FirebaseUser createUser = mAuth.getCurrentUser();
                            user.setAccessToken(createUser.getUid());

                            retrofit(user);
                        }else{
                            //failed
                            Log.w("SignUpPresenter", "createUserWithEmail:failure", task.getException());
                            i_signUpView.onFailedSignUp(task.getException().getLocalizedMessage());
                            i_signUpView.onProgressBarVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    void retrofit(User user)
    {
        GitHubServiceProvider.retrofit.createUser(user)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(context, "회원가입 실패 : 서버", Toast.LENGTH_SHORT).show();
                            deleteUserInFirebase();
                            return;
                        }
                        Log.d("SignUpPresenter", "회원가입 완료 : 서버");
                        i_signUpView.onSuccessSignUp();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(context, "회원가입 실패 : 서버", Toast.LENGTH_SHORT).show();
                        deleteUserInFirebase();
                    }
                });

    }

    void deleteUserInFirebase()
    {
        FirebaseUser createUser = mAuth.getCurrentUser();

        createUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(!task.isSuccessful()){
                    Log.d("SignUpPresenter", "파이어베이스 등록 후 서버이상, 파이어베이스삭제완료");
                }else{
                    Log.e("SignUpPresenter", "파이어베이스 등록 후 서버이상, 파이어베이스삭제 이상", task.getException());
                }
            }
        });

        i_signUpView.onProgressBarVisibility(View.INVISIBLE);
    }
}
