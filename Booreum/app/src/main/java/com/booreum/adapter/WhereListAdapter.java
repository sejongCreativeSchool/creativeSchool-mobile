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
import com.booreum.view.errandset.fragment.FromFragment;
import com.booreum.view.errandset.fragment.ToFragment;

public class WhereListAdapter extends BaseExpandableListAdapter  {

    Context context;
    EditText editText;
    String[] building ;
    ViewHolder viewHolder;
    Boolean isFrom; //true => From / false => To

    public WhereListAdapter(Context context, Boolean isFrom) {
        this.context = context;
        this.building = Building.building;
        this.isFrom = isFrom;
    }

//그룹 사이즈를 반환
    @Override
    public int getGroupCount() {
        return building.length;
    }

    //그룹 ID를 반환
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //그룹 포지션 반환
    @Override
    public Object getGroup(int groupPosition) {
        return editText;
    }

    //그룹 뷰 생성(그룹 각 뷰의 ROW)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String buildingStr = this.building[groupPosition];

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expendable_list_group_item, null);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        //그룹을 펼칠 때 또는 닫을 때 아이콘 변경
        if(isExpanded){
            //viewHolder.iv_image.setBackgroundColor(Color.GREEN);
        }else{
            //viewHolder.iv_image.setBackgroundColor(Color.WHITE);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.groupList_text);
        lblListHeader.setText(buildingStr);

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

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expendable_list_where_child_item, null);
        }

        EditText editText = (EditText) convertView
                .findViewById(R.id.childList_item);

        if(isLastChild)
            editText.setHint("주소를 입력하세요.");

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus == false && editText.getText() != null){
                    if(isFrom)
                        FromFragment.fromDetail = editText.getText().toString();
                    else
                        ToFragment.toDetail = editText.getText().toString();
                    editText.setText("");
                }
            }
        });

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
