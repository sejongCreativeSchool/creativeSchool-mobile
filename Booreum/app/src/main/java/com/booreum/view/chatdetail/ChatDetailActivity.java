package com.booreum.view.chatdetail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.booreum.Constant.SetTheme;
import com.booreum.Custom.RecyclerDecoration;
import com.booreum.Custom.Toolbar.CustomAppCompatForToolbar;
import com.booreum.adapter.ChatDetailListAdapter;
import com.booreum.adapter.ChatListAdapter;
import com.booreum.booreum.R;
import com.booreum.model.ChatData;
import com.booreum.model.ChatList;
import com.booreum.view.main.MainActivity;
import com.booreum.view.main.MainPresenter;
import com.booreum.view.main.fragment.chat.ChatPresenter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatDetailActivity extends CustomAppCompatForToolbar implements View.OnClickListener {

    TextView youState, name, department;
    EditText editText;
    ImageView profile;
    Button doneButton, sendButton;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ChatDetailListAdapter adapter ;

    FirebaseDatabase database ;
    DatabaseReference myRef;
    public static List<ChatList> helperList = new ArrayList<>();
    public static List<ChatList> neederList = new ArrayList<>();
    public static List<ChatData> chatData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetTheme.setTheme(this);
        setContentView(R.layout.activity_chat_detail);
        ActionBar actionBar = getHomeAsUpActionBar();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chats");

        initView();
        setListener();
    }

    private void setListener() {
        sendButton.setOnClickListener(this);
    }

    private void initView() {

        chatData = ChatListAdapter.chatData;

        youState = (TextView)findViewById(R.id.chatDetail_youState);
        name = (TextView)findViewById(R.id.chatDetail_name);
        department = (TextView)findViewById(R.id.chatDetail_department);
        editText = (EditText)findViewById(R.id.chatDetail_cht_edit);
        profile = (ImageView)findViewById(R.id.chatDetail_profile);
        doneButton = (Button)findViewById(R.id.chatDetail_done_button);
        sendButton = (Button)findViewById(R.id.chatDetail_send_button);
        recyclerView = (RecyclerView)findViewById(R.id.chatDetail_chatView);

        recyclerView.setHasFixedSize(true); // 성능강화
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerDecoration spaceDaoration = new RecyclerDecoration(5);
        recyclerView.addItemDecoration(spaceDaoration);

        adapter = new ChatDetailListAdapter(this);
        recyclerView.setAdapter(adapter);

        if(MainActivity.user.getHelper() && MainActivity.nowHelper)
            name.setText(ChatListAdapter.chatList.getNeederName());
        else
            name.setText(ChatListAdapter.chatList.getHelperName());
    }

    @Override
    protected void linkToolbar() {
        toolbar = findViewById(R.id.chatDetail_toolbar);
        setSupportActionBar(toolbar.getToolbar());
    }

    private String getNowTime(){
        // 현재시간을 msec 으로 구한다.
        long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        SimpleDateFormat sdfNow = new SimpleDateFormat("yy/MM/dd \nHH:mm");
        // nowDate 변수에 값을 저장한다.
        String formatDate = sdfNow.format(date);

        return formatDate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chatDetail_send_button:
                if(editText.getText()!=null) {
                    String str = editText.getText().toString();
                    ChatData data = new ChatData();
                    data.setMsg(str);
                    data.setName(MainPresenter.user.getName());
                    data.setTime(getNowTime());
                    chatData.add(data);
                    adapter.notifyDataSetChanged();

                    sendDatabase(data);

                    editText.setText("");
                }
        }
    }

    void sendDatabase(ChatData data){
        myRef.child("tjhkffktklasdjf;lskdjgfiasdjg;oiasdj").child("chat").push().setValue(data);
    }

    private void setDatabase() {
        ChildEventListener childEventListener_chatUser = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                neederList.clear();
                helperList.clear();

                ChatList chatList = dataSnapshot.getValue(ChatList.class);
                String key = dataSnapshot.getKey();

                for(DataSnapshot snapshot : dataSnapshot.child("chat").getChildren()){

                    ChatData data = snapshot.getValue(ChatData.class);
                    chatList.setList(data);
                }

                if(MainPresenter.user.getHelper() && MainActivity.nowHelper ){ // 지금 헬퍼면
                    if(chatList.getHelper().equals(MainPresenter.user.getAccessToken())) {
                        helperList.add(chatList);
                    }
                }
                else { // 지금 니더면
                    if(chatList.getNeeder().equals(MainPresenter.user.getAccessToken())) {
                        neederList.add(chatList);
                    }
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        myRef.addChildEventListener(childEventListener_chatUser);
    }
}