package com.booreum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.booreum.Constant.Building;
import com.booreum.booreum.R;
import com.booreum.model.Errand;
import com.booreum.model.ErrandResults;
import com.booreum.view.errandset.fragment.FromFragment;
import com.booreum.view.errandset.fragment.ToFragment;

public class HelperListAdapter extends BaseExpandableListAdapter {

    Context context;
    HelperListAdapter.ViewHolder viewHolder;
    ErrandResults results = new ErrandResults();

    public HelperListAdapter(Context context) {
        this.context = context;
    }

    //그룹 사이즈를 반환
    @Override
    public int getGroupCount() {
        return results.getData().size();
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
            viewHolder = new HelperListAdapter.ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.custom_helper_list_group_item, null);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (HelperListAdapter.ViewHolder)convertView.getTag();
        }
        //그룹을 펼칠 때 또는 닫을 때 아이콘 변경
        if(isExpanded){
            //viewHolder.iv_image.setBackgroundColor(Color.GREEN);
        }else{
            //viewHolder.iv_image.setBackgroundColor(Color.WHITE);
        }


        return convertView;
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
            convertView = infalInflater.inflate(R.layout.expendable_list_where_child_item, null);
        }

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
