package com.booreum.view.main.fragment.chat;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booreum.adapter.ChatListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatPresenter implements I_ChatPresenter {

    I_ChatView i_chatView;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    ArrayList<String> chatUserList = new ArrayList<>();

    private RecyclerView.Adapter adapter;

    public ChatPresenter(ChatFragment context) {
        i_chatView = context;
        this.database = FirebaseDatabase.getInstance();
        this.reference = database.getReference("user");
        //this.adapter= new ChatListAdapter(chatUserList);
    }

    @Override
    public void doIntentChatDetail() {

    }

    @Override
    public void getChatList() {
        Log.d("Chat_", "do");

    }
}
