package com.booreum.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.booreum.Constant.Const;
import com.booreum.booreum.R;
import com.booreum.model.ChatList;
import com.booreum.view.chatdetail.ChatDetailActivity;
import com.booreum.view.main.MainActivity;
import com.booreum.view.main.MainPresenter;
import com.booreum.view.main.fragment.chat.ChatPresenter;

import java.util.ArrayList;
import java.util.List;

public class ChatDetailListAdapter extends RecyclerView.Adapter<ChatDetailListAdapter.ViewHolder> {

    Context context;
    View view;

    public ChatDetailListAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ChatDetailListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_chat_detail_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatDetailListAdapter.ViewHolder holder, int position) {

        if (ChatListAdapter.chatData.get(position).getName().equals(MainPresenter.user.getName())) {//내가 말한 것이면
            holder.container.setGravity(Gravity.RIGHT);
            holder.timeRight.setVisibility(View.GONE);
            holder.timeLeft.setVisibility(View.VISIBLE);

        } else {//상대가 말한 것이면
            holder.textView.setBackgroundResource(R.drawable.border_chat_detail_you);
        }

        holder.textView.setText(ChatDetailActivity.chatData.get(position).getMsg());
        holder.timeRight.setText(ChatDetailActivity.chatData.get(position).getTime());
        holder.timeLeft.setText(ChatDetailActivity.chatData.get(position).getTime());



    }

    @Override
    public int getItemCount() {
        return ChatListAdapter.chatData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView textView, timeRight, timeLeft;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.chatDetail_chat_item_container);
            textView = itemView.findViewById(R.id.chatDetail_chat_item_text);
            timeRight = itemView.findViewById(R.id.chatDetail_chat_item_time_right);
            timeLeft = itemView.findViewById(R.id.chatDetail_chat_item_time_left);
        }
    }
}
