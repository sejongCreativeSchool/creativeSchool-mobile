package com.example.practicesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewMemoSetting extends AppCompatActivity {

    final static String NEW_TITLE_INTENT_KEY = "newTitle";
    final static String NEW_CONTENTS_INTENT_KEY = "newContents";
    final static String NEW_TIME_INTENT_KEY = "newTime";

    Button button;
    EditText title, contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo_setting);

        button = (Button)findViewById(R.id.button);
        title = (EditText)findViewById(R.id.newTitle);
        contents = (EditText)findViewById(R.id.newContants);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String getTime = simpleDate.format(date);


                Intent intent = new Intent();
                intent.putExtra(NEW_TITLE_INTENT_KEY, title.getText().toString());
                intent.putExtra(NEW_CONTENTS_INTENT_KEY, contents.getText().toString());
                intent.putExtra(NEW_TIME_INTENT_KEY, getTime);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
