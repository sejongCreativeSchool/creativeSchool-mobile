package com.booreum.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booreum.booreum.R;
import com.booreum.model.ChatList;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private ArrayList<ChatList> chatLists;

    public ChatListAdapter(ArrayList<ChatList> chatLists) {
        this.chatLists = chatLists;
        Log.d("chatList","dhkdn");
        Log.d("chatList","chatlist length : " + chatLists.size());

        Log.d("chatList","chatlist length : " + this.chatLists.size());
    }

    @NonNull
    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_chat_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        Log.d("chatList","온크레이트뷰홀더");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.ViewHolder holder, int position) {
        Glide.with(holder.itemView).load(chatLists.get(position).getPhotoUrl()).into(holder.profile);
        holder.name.setText(chatLists.get(position).getName());
        holder.lastChat.setText(chatLists.get(position).getLastChat());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "인텐트", Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("chatList","온바인드뷰홀더");
    }

    @Override
    public int getItemCount()  {
        return (chatLists != null ? chatLists.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView name, lastChat;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("chatList","뷰홀더내부");
            profile = itemView.findViewById(R.id.chat_frag_profile);
            name = itemView.findViewById(R.id.chat_frag_name);
            lastChat = itemView.findViewById(R.id.chat_frag_last_chat);
            button = itemView.findViewById(R.id.chat_frag_button);
        }
    }
}
