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
import com.booreum.model.CurrentUser;
import com.booreum.model.User;
import com.booreum.model.UserResult;
import com.booreum.model.UserResults;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements I_LoginPresenter, GoogleApiClient.OnConnectionFailedListener {

    static final int REQ_SIGN_GOOGLE = 100; //구글 로그인 결과 코드
    final static int EMAIL_LOGIN = 10;
    final static int GOOGLE_LOGIN = 20;

    Context context;
    I_LoginView i_loginView;
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
    public void resultLogin( GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ //로그인이 성공했으

                            retrofit_login(GOOGLE_LOGIN);
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
                                retrofit_login(EMAIL_LOGIN);
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

        i_loginView.onProgressBarVisibility(View.INVISIBLE);
    }

    void retrofit_snsSignUpAndLogin(User user){
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
                       isSuccess(user);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
    }

    void retrofit_login(int loginType) {
        //노멀로그인 불러오기
        GitHubServiceProvider.retrofit.loadUser(mAuth.getCurrentUser().getUid())
                .enqueue(new Callback<UserResult>() {
                    @Override
                    public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                        if(!response.isSuccessful()){
                            isFailed();
                        }

                        User user = response.body().data; // 현재유저데이터 저장

                        if(loginType == GOOGLE_LOGIN && user == null){
                            Log.d("LoginPresenter", "SNS 로그인, 첫 로그인 시 회원가입 후 로그인으로 이동");
                            FirebaseUser cUser = mAuth.getCurrentUser();
                            user = new User(cUser.getDisplayName(), cUser.getUid(), false, cUser.getPhoneNumber());
                            retrofit_snsSignUpAndLogin(user);
                            return;
                        }

                        isSuccess(user);
                    }

                    @Override
                    public void onFailure(Call<UserResult> call, Throwable t) {
                        Log.d("LoginPresenter", "load user failed");
                        Log.w("LoginPresenter", "response : ", t.getCause());
                        isFailed();
                    }
                });
    }

    void isSuccess(User user){
        Boolean result = true;
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(User.CURRNET_USER_INTENT_CODE, user);
        i_loginView.onLoginResult(result, intent);
    }

    void isFailed()
    {
        i_loginView.onProgressBarVisibility(View.GONE);
        mAuth.signOut();
        Toast.makeText(context, "서버와 연결할 수 없습니다", Toast.LENGTH_SHORT).show();
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
        FirebaseUser cUser = mAuth.getCurrentUser();
        User user = new User(cUser.getDisplayName(), cUser.getUid(), false, cUser.getPhoneNumber());
        isSuccess(user);
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQ_SIGN_GOOGLE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                GoogleSignInAccount account = result.getSignInAccount(); //account는 구글 로그인 정보를 담고 있음.(닉네임,프로필사진uri,이메일 등)
                resultLogin(account); // 로그인 결과값 출력 수행하는 메소
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
