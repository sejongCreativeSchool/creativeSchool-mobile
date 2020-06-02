package com.booreum.view.main.fragment.chat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.booreum.booreum.R;
import com.booreum.model.ChatList;

import java.util.ArrayList;

public class ChatFragment extends Fragment implements I_ChatView{

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<ChatList> chatLists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.chat_recycler);

        return view;
    }

    @Override
    public void onIntentChatDetail() {

    }
}
