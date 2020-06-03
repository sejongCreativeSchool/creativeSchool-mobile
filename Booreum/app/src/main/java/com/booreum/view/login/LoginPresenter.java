package com.booreum.view.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.booreum.booreum.R;
import com.booreum.Constant.GitHubService;
import com.booreum.Constant.GitHubServiceProvider;
import com.booreum.Constant.PreferenceManager;
import com.booreum.model.User;
import com.booreum.view.main.MainActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements I_LoginPresenter, GoogleApiClient.OnConnectionFailedListener {

    static final int REQ_SIGN_GOOGLE = 100; //구글 로그인 결과 코드
    static final int NOMAL_LOGIN = 1; //일반로그인 결과 코드
    static final int GOOGLE_LOGIN = 2; //구글로그인 결과 코드

    final static int RETROFIT_CREATE_USER = 100; // 유저 회원가입 레트로핏 코드
    final static int RETROFIT_LOAD_USER = 200; // 유저 회원가입 레트로핏 코드

    Context context;
    I_LoginView i_loginView;
    User user;
    FirebaseAuth mAuth;
    GitHubService retrofit;
    private GoogleApiClient googleApiClient; //구글 API 클라이언트 객

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
    public void resultLogin(int sns, GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ //로그인이 성공했으
                            Toast.makeText(context, "로그인성공",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, MainActivity.class);

                            /**
                             * 통신통해서 유저 정보 가져오기
                             */
                        }
                        else {
                            Toast.makeText(context, "로그인실패",Toast.LENGTH_SHORT).show();
                            i_loginView.onSocialProgressBarVisibility(View.VISIBLE);
                        }
                    }
                });
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
                                retrofit(RETROFIT_LOAD_USER);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("LoginPresent", "signInWithEmail:failure", task.getException());
                                result = false;

                                i_loginView.onLoginResult(result, null);
                                i_loginView.onProgressBarVisibility(View.GONE);
                            }
                        }
                    });
    }

    void retrofit(int retrofit_code)
    {
        //개별유저 불러오기
        //레트로핏통신 실패서 로그인다시
        Log.d("LoginPresenter", "in LoginPresenter retrofit func.");
        Log.d("LoginPresenter", "getCurrentUser.Getid : "+mAuth.getCurrentUser().getUid());

        GitHubServiceProvider.retrofit.loadUser(mAuth.getCurrentUser().getUid())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("LoginPresenter", "load user success");
                        Log.d("LoginPresenter", "response : " + response.body());

                        User user = response.body();

                        Log.d("LoginPresenter", "response : " + response.isSuccessful());
                        Log.d("LoginPresenter", "response : " + response.errorBody());
                        Log.d("LoginPresenter", "response : " + response.raw());
                        Log.d("LoginPresenter", "response : " + response.body().toString());
                        Log.d("LoginPresenter", "response : " + response.message());
                        Log.d("LoginPresenter", "response : " + response.code());

                        Log.d("LoginPresenter", "User : " + user.getName() + user.getPhone() + user.getAccessToken());
                        Log.d("LoginPresenter", "response : " + response.body().getName() + response.body().getPhone() + response.body().getAccessToken());
                        Intent intent = new Intent(context, MainActivity.class);
                        i_loginView.onLoginResult(true, intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("LoginPresenter", "load user failed");
                        Log.w("LoginPresenter", "response : ", t.getCause());
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
        i_loginView.onSocialProgressBarVisibility(View.VISIBLE);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(context/*프레그먼트에서는 getContext*/)
                .enableAutoManage((FragmentActivity) context,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient); // 구글에서 만든 구글 인증하는 곳
        i_loginView.onLoginGoogle(intent);

        /**
         * 구글로그인시 서버로 데이터 전송 혹은 불러오기
         */
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQ_SIGN_GOOGLE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                GoogleSignInAccount account = result.getSignInAccount(); //account는 구글 로그인 정보를 담고 있음.(닉네임,프로필사진uri,이메일 등)
                resultLogin(NOMAL_LOGIN, account); // 로그인 결과값 출력 수행하는 메소
            }
        }
    }


    @Override
    public void setProgressBarVisibility(int visibility) {
        i_loginView.onProgressBarVisibility(visibility);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
