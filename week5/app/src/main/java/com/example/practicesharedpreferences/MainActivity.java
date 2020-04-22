package com.example.practicesharedpreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity
        implements CustomAdapter.OnListItemSelectedInterface, CustomAdapter.OnListItemLongSelectedInterface {

    final static String DEFAULT_PREF_MEMO = "default";
    final static int NEWMEMO_REQUEST_CODE = 100;
    final static int DETAILMEMO_REQUEST_CODE = 200;

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

        adapter = new CustomAdapter(arrayList, this, this, this);
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
                String newTitle = data.getStringExtra(Memo.TITLE_INTENT_KEY);
                String newContents = data.getStringExtra(Memo.CONTENTS_INTENT_KEY);
                String newTime = data.getStringExtra(Memo.TIME_INTENT_KEY);
                arrayList.add(new Memo(newTitle, newContents, newTime));
                Toast.makeText(this, "작성완료1", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                savePreferences();
            }
            else
            {
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == DETAILMEMO_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                String newTitle = data.getStringExtra(Memo.TITLE_INTENT_KEY);
                String newContents = data.getStringExtra(Memo.CONTENTS_INTENT_KEY);
                String newTime = data.getStringExtra(Memo.TIME_INTENT_KEY);
                int position = data.getIntExtra("position", -1);
                if(position != -1)
                {
                    arrayList.add(position, new Memo(newTitle, newContents, newTime));
                }

                Toast.makeText(this, "작성완료2", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                savePreferences();
            }
            else
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    void savePreferences()
    {
        Collections.sort(arrayList, new DescendingObj());
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        mEditor.putString(DEFAULT_PREF_MEMO, json);
        mEditor.commit();
    }

    void getPreferences()
    {
        Collections.sort(arrayList, new DescendingObj());
        Gson gson = new Gson();
        String json = mPrefs.getString(DEFAULT_PREF_MEMO, "EMPTY");
        Type type = new TypeToken<ArrayList<Memo>>() {}.getType();
        arrayList = gson.fromJson(json, type);
    }

    @Override
    public void onItemLongSelected(View v, final int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        dialog.setMessage("삭제하시겠습니까?")
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "cancelled", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.remove(position);
                        adapter.notifyDataSetChanged();
                        savePreferences();
                    }
                })
                .show();
    }

    @Override
    public void onItemSelected(View v, int position) {


        Intent intent = new Intent(this, NewMemoSetting.class);
        intent.putExtra(Memo.TITLE_INTENT_KEY, arrayList.get(position).getTitle());
        intent.putExtra(Memo.CONTENTS_INTENT_KEY, arrayList.get(position).getText());
        intent.putExtra("position",position);
        startActivityForResult(intent, DETAILMEMO_REQUEST_CODE);
    }

    // String 내림차순
    class DescendingObj implements Comparator<Memo> {

        @Override
        public int compare(Memo o1, Memo o2) {
            return o2.getTime().compareTo(o1.getTime());
        }

    }


}

