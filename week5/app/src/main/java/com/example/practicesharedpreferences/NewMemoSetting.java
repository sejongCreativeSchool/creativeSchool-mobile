package com.example.practicesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewMemoSetting extends AppCompatActivity {

    Button button;
    EditText title, contents;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo_setting);

        button = (Button)findViewById(R.id.button);
        title = (EditText)findViewById(R.id.newTitle);
        contents = (EditText)findViewById(R.id.newContants);

        Intent intent2 = getIntent();
        if( intent2.getStringExtra(Memo.TITLE_INTENT_KEY)!=null ){
            title.setText(intent2.getStringExtra(Memo.TITLE_INTENT_KEY));
            contents.setText(intent2.getStringExtra(Memo.CONTENTS_INTENT_KEY));
            position = intent2.getIntExtra("position",-1);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().isEmpty() || contents.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "모든 내용을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String getTime = simpleDate.format(date);


                Intent intent = new Intent();
                intent.putExtra(Memo.TITLE_INTENT_KEY, title.getText().toString());
                intent.putExtra(Memo.CONTENTS_INTENT_KEY, contents.getText().toString());
                intent.putExtra(Memo.TIME_INTENT_KEY, getTime);
                intent.putExtra("position",position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
