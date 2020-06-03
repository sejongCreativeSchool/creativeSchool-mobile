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
import com.booreum.model.ChatList;

import java.util.ArrayList;

public class ChatFragment extends Fragment implements I_ChatView{

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ChatList> chatLists;

    ChatList chatList[] = new ChatList[100]; //test

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);

        initView();


        return view;
    }

    private void initView()
    {
        recyclerView = view.findViewById(R.id.chat_recycler);
        recyclerView.setHasFixedSize(true); // 성능강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerDecoration spaceDaoration = new RecyclerDecoration(5);
        recyclerView.addItemDecoration(spaceDaoration);

        chatLists = new ArrayList<ChatList>();
        setTestArrayList();

        adapter = new ChatListAdapter(chatLists);

        recyclerView.setAdapter(adapter);
    }

    private void setTestArrayList()
    {

        for(int i=0; i< chatList.length; i++){
            chatList[i] = new ChatList("1", "1", "1", "1");
            chatLists.add(chatList[i]);
        }
    }

    @Override
    public void onIntentChatDetail() {

    }
}
