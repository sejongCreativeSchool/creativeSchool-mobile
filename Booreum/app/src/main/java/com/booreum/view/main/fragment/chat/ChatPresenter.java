package com.booreum.view.main.fragment.chat;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.booreum.model.ChatData;
import com.booreum.model.ChatList;
import com.booreum.view.main.MainActivity;
import com.booreum.view.main.MainPresenter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatPresenter implements I_ChatPresenter {

    I_ChatView i_chatView;
    Context context;
    FirebaseDatabase database ;
    DatabaseReference myRef;
    public static List<ChatList> helperList = new ArrayList<>();
    public static List<ChatList> neederList = new ArrayList<>();


    public ChatPresenter( Context context2) {
        i_chatView = new ChatFragment();
        this.context = context2;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chats");

        setDatabase();
    }

    private void setDatabase() {
        ChildEventListener childEventListener_chatUser = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                neederList.clear();
                helperList.clear();

                Log.d("ttt",dataSnapshot.getValue().toString());
                ChatList chatList = dataSnapshot.getValue(ChatList.class);
                String key = dataSnapshot.getKey();
                Log.d("ttt", "key : " + key);
                Log.d("ttt", dataSnapshot.child("chat").toString());
                Log.d("ttt", dataSnapshot.child("chat").getValue().toString());

                for(DataSnapshot snapshot : dataSnapshot.child("chat").getChildren()){
                    Log.d("ttt", "snapshot : " + snapshot.getValue(ChatData.class).getMsg());
                    Log.d("ttt", "snapshot : " + snapshot.getValue(ChatData.class).getTime());
                    ChatData data = snapshot.getValue(ChatData.class);
                    chatList.setList(data);
                }

                if(MainPresenter.user.getHelper() && MainActivity.nowHelper ){ // 지금 헬퍼면
                    if(chatList.getHelper().equals(MainPresenter.user.getAccessToken())) {
                        helperList.add(chatList);
                        i_chatView.onChangeData();
                    }
                }
                else { // 지금 니더면
                    if(chatList.getNeeder().equals(MainPresenter.user.getAccessToken())) {
                        neederList.add(chatList);
                        i_chatView.onChangeData();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                neederList.clear();
                helperList.clear();

                Log.d("ttt",dataSnapshot.getValue().toString());
                ChatList chatList = dataSnapshot.getValue(ChatList.class);

                for(DataSnapshot snapshot : dataSnapshot.child("chat").getChildren()){
                    Log.d("ttt", "snapshot : " + snapshot.getValue(ChatData.class).getMsg());
                    Log.d("ttt", "snapshot : " + snapshot.getValue(ChatData.class).getTime());
                    ChatData data = snapshot.getValue(ChatData.class);
                    chatList.setList(data);
                }

                if(MainPresenter.user.getHelper() && MainActivity.nowHelper){ // 지금 헬퍼면
                    if(chatList.getHelper().equals(MainPresenter.user.getAccessToken())) {
                        helperList.add(chatList);
                        i_chatView.onChangeData();
                    }
                }
                else { // 지금 니더면
                    if(chatList.getNeeder().equals(MainPresenter.user.getAccessToken())) {
                        neederList.add(chatList);
                        i_chatView.onChangeData();
                    }
                }
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

    @Override
    public void doIntentChatDetail() {

    }

}
