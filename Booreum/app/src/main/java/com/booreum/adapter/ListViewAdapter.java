package com.booreum.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.booreum.booreum.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter  {

    private Context context;
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<String> stringList = new ArrayList<>() ;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_setting, parent, false);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.listview_item_textview);

        String str = stringList.get(position);
        textView.setText(str);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0)
                    Toast.makeText(context, "0", Toast.LENGTH_SHORT).show();
                else if(position ==1)
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                else if(position ==2)
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                else if(position ==3)
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String str) {
        String item = str ;

        stringList.add(item);
    }

}
