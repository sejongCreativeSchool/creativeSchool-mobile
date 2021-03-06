package com.booreum.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.booreum.booreum.R;
import com.booreum.model.ChatList;
import com.booreum.model.ErrandResults;
import com.booreum.view.main.MainPresenter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HelperListAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    Context context;
    ViewHolder viewHolder;
    ErrandResults results ;



    public HelperListAdapter(Context context) {
        this.context = context;


    }



    public void setResults(ErrandResults errandResults){
        results = new ErrandResults();
        this.results = errandResults;
    }

    //그룹 사이즈를 반환
    @Override
    public int getGroupCount() {
        return (results == null )? 0 : results.getData().size();
    }

    //그룹 ID를 반환
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //그룹 포지션 반환
    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    //그룹 뷰 생성(그룹 각 뷰의 ROW)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.custom_helper_list_group_item, null);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (HelperListAdapter.ViewHolder)convertView.getTag();
        }


        ImageView profile, arrow, category;
        Button button;
        TextView name,point;

        profile = (ImageView)convertView.findViewById(R.id.helperlist_profile);
        arrow = (ImageView)convertView.findViewById(R.id.arrow);
        category = (ImageView)convertView.findViewById(R.id.list_category);
        button = (Button)convertView.findViewById(R.id.helperlist_button);
        name = (TextView)convertView.findViewById(R.id.helperlist_name);
        point = (TextView)convertView.findViewById(R.id.list_point);


        //그룹을 펼칠 때 또는 닫을 때 아이콘 변경
        if(isExpanded){
            arrow.setRotation(180);
        }else{
            arrow.setRotation(0);
        }

        //profile = results.getData().get(groupPosition).getUser().getProfile();
        category.setImageResource(setCategoryImage(groupPosition));
        name.setText(results.getData().get(groupPosition).getUser().getName());
        point.setText(String.valueOf(results.getData().get(groupPosition).getPrice()) + "p");

        button.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.helperlist_button:

                break;
        }
    }

    private int setCategoryImage(int groupPosition) {
        switch (groupPosition) {
            case 1:
                return R.drawable.icon_bring_simple;
            case 2:
                return R.drawable.icon_buy_simple;
            case 3:
                return R.drawable.icon_delivery_simple;
            case 4:
                return R.drawable.icon_submit_simple;
            case 5:
                return R.drawable.icon_print_simple;
            case 6:
                return R.drawable.icon_together_simple;
            case 7:
                return R.drawable.icon_instead_simple;
            case 8:
                return R.drawable.icon_etc_simple;
        }
        return R.drawable.icon_etc_simple;
    }


    //차일드 뷰 반환
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    //차일드 뷰의 사이즈 반환
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }


    //차일드 뷰 ID 반환
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //차일드 뷰 생성(각 차일드 뷰의 (ROW)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.custom_helper_list_child_item, null);
        }

        TextView category, point, from, to;
        category = (TextView)convertView.findViewById(R.id.list_child_category);
        point = (TextView)convertView.findViewById(R.id.list_child_point);
        from = (TextView)convertView.findViewById(R.id.list_child_from);
        to = (TextView)convertView.findViewById(R.id.list_child_to);

        category.setText(results.getData().get(groupPosition).getCategory());
        point.setText(String.valueOf(results.getData().get(groupPosition).getPrice()));
        from.setText(results.getData().get(groupPosition).getFrom());
        to.setText(results.getData().get(groupPosition).getTo());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    //뷰홀더 클래스 생
    class ViewHolder {

    }

}
