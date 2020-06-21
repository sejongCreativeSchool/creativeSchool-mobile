package com.booreum.view.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.booreum.Constant.GitHubServiceProvider;
import com.booreum.Constant.PreferenceManager;
import com.booreum.booreum.R;
import com.booreum.model.User;
import com.booreum.model.UserResult;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.booreum.view.main.MainActivity.tabLayout;

public class MainPresenter implements I_MainPresenter{

    private Context context;
    private I_MainView i_mainView;
    public static FirebaseAuth mAuth;
    public static FirebaseUser authUser;
    public static User user;

    public MainPresenter(Context context, I_MainView mainView, User user) {

        this.context = context;
        this.i_mainView = mainView;
        this.mAuth = FirebaseAuth.getInstance();
        authUser = mAuth.getCurrentUser();
        MainPresenter.user = user;
        //setUser();
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
                        Log.d("Setting", "유저받아옴");
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
                if(PreferenceManager.isHelper(context) && user.getHelper())
                    i_mainView.setTabTitle("리스트");
                else
                    i_mainView.setTabTitle("카테고리");
                break;
            case 1:
                i_mainView.setTabTitle("채팅");
                break;
            case 2:
                i_mainView.setTabTitle("설정");
                break;
        }
        return null;
    }


}