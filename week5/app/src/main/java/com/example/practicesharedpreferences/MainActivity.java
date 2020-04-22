package com.example.practicesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String DEFAULT_PREF_MEMO = "default";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Memo> arrayList;

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mPrefs.edit();

        getPreferences();

        adapter = new CustomAdapter(arrayList, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

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

