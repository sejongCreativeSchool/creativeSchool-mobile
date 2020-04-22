package com.example.practicesharedpreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String DEFAULT_PREF_MEMO = "default";
    final static int NEWMEMO_REQUEST_CODE = 100;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button button;
    ArrayList<Memo> arrayList;

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        button = (Button)findViewById(R.id.button);
        arrayList = new ArrayList<>();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mPrefs.edit();

        getPreferences();

        adapter = new CustomAdapter(arrayList, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewMemoSetting.class);
                startActivityForResult(intent, NEWMEMO_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEWMEMO_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                String newTitle = data.getStringExtra(NewMemoSetting.NEW_TITLE_INTENT_KEY);
                String newContents = data.getStringExtra(NewMemoSetting.NEW_CONTENTS_INTENT_KEY);
                String newTime = data.getStringExtra(NewMemoSetting.NEW_TIME_INTENT_KEY);
                arrayList.add(new Memo(newTitle, newContents, newTime));
                Toast.makeText(this, "작성완료", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                savePreferences();
            }
            else
            {
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void savePreferences()
    {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        mEditor.putString(DEFAULT_PREF_MEMO, json);
        mEditor.commit();
    }

    void getPreferences()
    {
        Gson gson = new Gson();
        String json = mPrefs.getString(DEFAULT_PREF_MEMO, "EMPTY");
        Type type = new TypeToken<ArrayList<Memo>>() {}.getType();
        arrayList = gson.fromJson(json, type);
    }
}

