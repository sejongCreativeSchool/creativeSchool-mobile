package com.booreum.view.main.fragment.chat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.booreum.Custom.RecyclerDecoration;
import com.booreum.adapter.ChatListAdapter;
import com.booreum.booreum.R;
import com.booreum.model.ChatData;
import com.booreum.model.ChatList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatFragment extends Fragment implements I_ChatView{

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private I_ChatPresenter i_chatPresenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);

        initView();


        return view;
    }

    private void initView()
    {
        /*

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child(FirebaseAuth.getInstance().getUid()).child("5eo2IuITfzLvum3clERFqDQk5ws1").setValue(new ChatData("hi","you"));
        reference.child("user").child(FirebaseAuth.getInstance().getUid()).push().setValue("5eo2IuITfzLvum3clERFqDQk5ws1");
        */


        recyclerView = view.findViewById(R.id.chat_recycler);
        recyclerView.setHasFixedSize(true); // 성능강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerDecoration spaceDaoration = new RecyclerDecoration(5);
        recyclerView.addItemDecoration(spaceDaoration);

        i_chatPresenter = new ChatPresenter(this,recyclerView);
    }



    @Override
    public void onIntentChatDetail() {

    }
}
