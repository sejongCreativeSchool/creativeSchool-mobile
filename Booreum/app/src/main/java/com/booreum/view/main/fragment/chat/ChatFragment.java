package com.booreum.view.main.fragment.chat;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

    Context context;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private I_ChatPresenter i_chatPresenter;
    private static LinearLayout not_chat;
    static ChatListAdapter chatListAdapter;

    public ChatFragment() {
    }

    public ChatFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * 먼저 프레젠트나 메인에서 통신해서 채팅있으면 그대로,없으면 없는 레이아웃으로.
         */
        view = inflater.inflate(R.layout.fragment_chat, container, false);

        i_chatPresenter = new ChatPresenter(context);

        initView();

        return view;
    }

    private void initView()
    {
        not_chat = view.findViewById(R.id.chat_notChat);

        recyclerView = view.findViewById(R.id.chat_recycler);
        recyclerView.setHasFixedSize(true); // 성능강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerDecoration spaceDaoration = new RecyclerDecoration(5);
        recyclerView.addItemDecoration(spaceDaoration);

        chatListAdapter= new ChatListAdapter(context);
        recyclerView.setAdapter(chatListAdapter);
    }



    @Override
    public void onIntentChatDetail() {

    }

    @Override
    public void onChangeData() {
        chatListAdapter.notifyDataSetChanged();
        chatListAdapter.updateChatDetail();
        not_chat.setVisibility(View.GONE);
    }


}
