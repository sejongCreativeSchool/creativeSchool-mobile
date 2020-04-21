package com.example.practicemessagingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<User> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
        Glide.with(holder.itemView).load(arrayList.get(position).getProfile()).into(holder.profile);
        holder.nickName.setText(arrayList.get(position).getNickname());
        /*
        라스트컨텐츠는 채팅관련
         */
        //holder.lastContext.setText(arrayList.get(position).getLastContext());
        if(arrayList.get(position).isNewmark())
            holder.newMark.setVisibility(View.VISIBLE);
        else
            holder.newMark.setVisibility(View.INVISIBLE);
        Glide.with(holder.itemView).load(R.drawable.reddot).into(holder.newMark);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.get(position).setNewmark(false);
                holder.newMark.setVisibility(View.INVISIBLE);
                /*
                뉴마커 변경에 있어 데이터베이스 변경은 신경쓰지 않음.
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView nickName;
        TextView lastContext;
        ImageView newMark;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profile = itemView.findViewById(R.id.listProfile);
            this.nickName = itemView.findViewById(R.id.listNickname);
            this.lastContext = itemView.findViewById(R.id.listLastContext);
            this.newMark = itemView.findViewById(R.id.listNewMark);

            newMark.setVisibility(View.INVISIBLE);
        }
    }
}
