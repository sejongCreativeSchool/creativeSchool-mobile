package com.booreum.booreum.view.signup;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.booreum.booreum.R;
import com.booreum.booreum.constant.CheckValid;
import com.booreum.booreum.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
    public void doSignUp(User user, String pwStr, String pwCheckStr) {

        if ((!CheckValid.isValidEmail(context, user.getId()))
                || (!CheckValid.isValidPassword(context, pwStr))) //이메일, 비밀번호 형식 확인
        {
            i_signUpView.onFailedSignUp("가입실패");
            i_signUpView.resetView();
            return;
        }

        if (!CheckValid.isValidNotEmpty(context, user.getName(), user.getPhone(), pwCheckStr)) //빈칸확인
        {
            i_signUpView.onFailedSignUp("가입실패");
            i_signUpView.resetView();
            return;
        }

        if ((!pwCheckStr.isEmpty()) && (!pwStr.equals(pwCheckStr))) {
            i_signUpView.isPasswordCheckerConfirm();
            i_signUpView.resetView();
            return;
        }

        mAuth.createUserWithEmailAndPassword(user.getId(), pwStr)
                .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //success
                            Log.d("SignUpPresenter", "createUserWithEmail:success");
                        }else{
                            //failed
                            Log.w("SignUpPresenter", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        i_signUpView.resetView();
        i_signUpView.onSuccessSignUp();

    }
}
