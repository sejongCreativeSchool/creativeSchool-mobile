package com.example.practicesharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    ArrayList<Memo> arrayList;
    Context context;
    private OnListItemSelectedInterface mListener;
    private OnListItemLongSelectedInterface mLongListener;

    public CustomAdapter(ArrayList<Memo> _arrayList, Context _context,
                         OnListItemSelectedInterface _mListener, OnListItemLongSelectedInterface _mLongListner) {
        arrayList = _arrayList;
        context = _context;
        mListener = _mListener;
        mLongListener = _mLongListner;
    }


    public interface OnListItemLongSelectedInterface {
        void onItemLongSelected(View v, int position);
    }

    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }




    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {

        if(arrayList != null) {
            holder.title.setText(arrayList.get(position).getTitle());
            holder.text.setText(arrayList.get(position).getText());
            holder.time.setText(arrayList.get(position).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView text;
        TextView time;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.listTitle);
            text = itemView.findViewById(R.id.listContents);
            time = itemView.findViewById(R.id.listTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        mListener.onItemSelected(v, pos);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        mLongListener.onItemLongSelected(v,pos );
                    }
                    return false;
                }
            });

        }
    }
}
