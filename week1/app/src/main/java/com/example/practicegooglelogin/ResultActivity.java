package com.example.practicegooglelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.text);

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");
        String photoUrl = intent.getStringExtra("photoUrl");
        //인텐트 값 받아오기

        textView.setText(nickname);
        Glide.with(this).load(photoUrl).into(imageView);//프로필 url을 이미지뷰에 세팅.




    }
}
