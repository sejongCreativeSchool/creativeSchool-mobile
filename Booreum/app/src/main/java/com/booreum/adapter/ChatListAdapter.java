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

import com.booreum.Constant.GitHubServiceProvider;
import com.booreum.booreum.R;
import com.booreum.model.ChatList;
import com.booreum.model.User;
import com.booreum.model.UserResult;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private ArrayList<String> chatLists;
    private ArrayList<User> chatListData;

    public ChatListAdapter(ArrayList<String> chatLists) {
        this.chatLists = chatLists;
        for (int i = 0; i < chatLists.size(); i++) {
            setChatListData(chatLists.get(i));
        }
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

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.ViewHolder holder, int position) {
        //Glide.with(holder.itemView).load(chatLists.get(position).getPhotoUrl()).into(holder.profile);
        //holder.name.setText(chatListData.get(position).getName());
        //holder.lastChat.setText(chatListData.get(position).getLastChat());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "인텐트", Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("chatList", "온바인드뷰홀더");
    }

    @Override
    public int getItemCount() {
        return (chatLists != null ? chatLists.size() : 0);
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

    void setChatListData(String accessToken) {
        GitHubServiceProvider.retrofit.loadUser(accessToken)
                .enqueue(new Callback<UserResult>() {
                    @Override
                    public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                        if (!response.isSuccessful()) {
                            return;
                        }
                        User user = response.body().data;
                        chatListData.add(user);
                    }

                    @Override
                    public void onFailure(Call<UserResult> call, Throwable t) {

                    }
                });
    }

}
