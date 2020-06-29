package com.booreum.adapter;

import android.content.Context;
import android.content.Intent;
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

import com.booreum.Constant.GitHubServiceProvider;
import com.booreum.booreum.R;
import com.booreum.model.ChatData;
import com.booreum.model.ChatList;
import com.booreum.model.User;
import com.booreum.model.UserResult;
import com.booreum.view.chatdetail.ChatDetailActivity;
import com.booreum.view.main.MainActivity;
import com.booreum.view.main.MainPresenter;
import com.booreum.view.main.fragment.chat.ChatPresenter;
import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    public static List<ChatData> chatData = new ArrayList<>(); //for chatDetail
    public static ChatList chatList;

    Context context;

    public ChatListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_chat_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        Log.d("chatList", "온크레이트뷰홀더");

        return viewHolder;
    }

    public void updateChatDetail(){

    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.ViewHolder holder, int position) {
        //Glide.with(holder.itemView).load(chatLists.get(position).getPhotoUrl()).into(holder.profile);
        if(MainActivity.nowHelper){
            holder.name.setText(ChatPresenter.helperList.get(position).getNeederName());
            holder.lastChat.setText(ChatPresenter.helperList.get(position).getLastMsg());
        }
        else{
            holder.name.setText(ChatPresenter.neederList.get(position).getHelperName());
            holder.lastChat.setText(ChatPresenter.neederList.get(position).getLastMsg());
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.nowHelper){
                    chatList = ChatPresenter.helperList.get(position);
                   chatData = ChatPresenter.helperList.get(position).getList();

                }
                else{
                    chatList = ChatPresenter.neederList.get(position);
                    chatData = ChatPresenter.neederList.get(position).getList();
                }
                Intent intent = new Intent(context, ChatDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int nowHelperNum;
        int nowNeederNum;

        if(MainActivity.nowHelper)
            return (ChatPresenter.helperList != null ? ChatPresenter.helperList.size() : 0);
        else
            return (ChatPresenter.neederList != null ? ChatPresenter.neederList.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView name, lastChat;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("chatList", "뷰홀더내부");
            profile = itemView.findViewById(R.id.chat_frag_profile);
            name = itemView.findViewById(R.id.chat_frag_name);
            lastChat = itemView.findViewById(R.id.chat_frag_last_chat);
            button = itemView.findViewById(R.id.chat_frag_button);
        }
    }

}
