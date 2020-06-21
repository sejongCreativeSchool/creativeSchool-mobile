package com.booreum.view.errandset.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.booreum.Constant.Building;
import com.booreum.adapter.WhereListAdapter;
import com.booreum.booreum.R;

public class FromFragment extends Fragment {

    public static String fromWhere="";
    public static String fromDetail="";

    private static ExpandableListView expandableListView;
    private WhereListAdapter adapter;
    private Context context;

    public FromFragment(Context context) {
        this.context = context;
    }

    View pre_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_from, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.from_listview);

        adapter = new WhereListAdapter(context, true);
        expandableListView.setAdapter(adapter);

        // 리스트뷰 그룹(부모)뷰를 클릭 했을 경우
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                fromWhere = Building.building[groupPosition];
                Log.d("Errand_", "그룹클릭리스너 : " + groupPosition);
                return false;
            }
        });

        // 그룹이 열릴 경우 이벤트 발
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition==0) expandableListView.collapseGroup(0);
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        View header = getLayoutInflater().inflate(R.layout.listview_header, null, false);
        TextView tv = header.findViewById(R.id.list_header_title);
        tv.setText("어디서");
        expandableListView.addHeaderView(header);

        return view;
    }

    public static void collapseAllGroup(){
        for(int i=0; i< Building.building.length; i++)
            expandableListView.collapseGroup(i);
    }
}