package com.booreum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.DatePicker;

import com.booreum.booreum.R;
import com.booreum.view.errandset.fragment.WhenFragment;

public class WhenListAdapter extends BaseExpandableListAdapter {

    Context context;
    WhenListAdapter.ViewHolder viewHolder;
    String[] group = new String[]{"날짜", "시간"};

    public WhenListAdapter(Context context) {
        this.context = context;
    }

    //그룹 사이즈를 반환
    @Override
    public int getGroupCount() {
        return group.length;
    }

    //그룹 ID를 반환
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //그룹 포지션 반환
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    //그룹 뷰 생성(그룹 각 뷰의 ROW)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolder = new WhenListAdapter.ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expendable_list_group_item, null);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (WhenListAdapter.ViewHolder) convertView.getTag();
        }
        //그룹을 펼칠 때 또는 닫을 때 아이콘 변경
        if (isExpanded) {
            //viewHolder.iv_image.setBackgroundColor(Color.GREEN);
        } else {
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

            convertView = infalInflater.inflate(R.layout.expendable_list_when_child_item1, null);
            if (isLastChild)
                convertView = infalInflater.inflate(R.layout.expendable_list_when_child_item2, null);
        }

        if (!isLastChild) {
            DatePicker datePicker = (DatePicker) convertView.findViewById(R.id.datePicker);
            String year = String.valueOf(datePicker.getYear());
            WhenFragment.year = year;
            String month = String.valueOf(datePicker.getMonth());
            WhenFragment.month = month;
            String day = String.valueOf(datePicker.getDayOfMonth());
            WhenFragment.day = day;
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
