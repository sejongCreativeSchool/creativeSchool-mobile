package com.booreum.view.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.booreum.Constant.GitHubServiceProvider;
import com.booreum.model.User;
import com.booreum.model.UserResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements I_MainPresenter{

    private Context context;
    private I_MainView mainView;
    public static FirebaseAuth mAuth;
    public static FirebaseUser authUser;
    public static User user;

    public MainPresenter(Context context, I_MainView mainView) {

        this.mainView = mainView;
        this.mAuth = FirebaseAuth.getInstance();
        authUser = mAuth.getCurrentUser();
        setUser();
    }

    private void setUser() {
        GitHubServiceProvider.retrofit.loadUser(authUser.getUid())
                .enqueue(new Callback<UserResult>() {
                    @Override
                    public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(context, "유저 불러오기 서버 에러", Toast.LENGTH_SHORT).show();
                            Log.d("MainPresenter", "User Retrofit error in onResponse");
                        }

                        User user1 = response.body().data;
                        MainPresenter.user = user1;
                    }
                    @Override
                    public void onFailure(Call<UserResult> call, Throwable t) {
                        Toast.makeText(context, "유저 불러오기 서버 에러", Toast.LENGTH_SHORT).show();
                        Log.d("MainPresenter", "User Retrofit error in onResponse");
                    }
                });
    }

    @Override
    public String doTabTitle(int position) {
        switch (position) {
            case 0:
                mainView.setTabTitle("카테고리");break;
            case 1:
                mainView.setTabTitle("채팅");break;
            case 2:
                mainView.setTabTitle("설정");break;
        }
        return null;
    }
}
