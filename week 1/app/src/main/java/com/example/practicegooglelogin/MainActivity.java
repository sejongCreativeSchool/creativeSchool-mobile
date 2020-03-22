package com.example.practicegooglelogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth mAuth; // 구글 로그인 버튼
    private SignInButton googleLoginBut; //파이어베이스 인증 객체
    private GoogleApiClient googleApiClient; //구글 API 클라이언트 객
    private  static final int REQ_SIGN_GOOGLE = 100; //구글 로그인 결과 코드


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleLoginBut = (SignInButton)findViewById(R.id.loginGoogle);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();//구글 signin 버튼 할때 옵션 정리 해주는 곳.

        googleApiClient = new GoogleApiClient.Builder(this/*프레그먼트에서는 getContext*/)
        .enableAutoManage(this,this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
        .build();

        mAuth =FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화

        googleLoginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient); // 구글에서 만든 구글 인증하는 곳
                startActivityForResult(intent, REQ_SIGN_GOOGLE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//구글로그인 인증을 요청했을 때 결과값을 되돌려받는 곳.
        super.onActivityResult(requestCode, resultCode, data);
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

    private void resultLogin(final GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ //로그인이 성공했으
                            Toast.makeText(MainActivity.this, "로그인성공",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);

                            intent.putExtra("nickname",account.getDisplayName());
                            intent.putExtra("photoUrl",String.valueOf(account.getPhotoUrl()));

                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "로그인실패",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
