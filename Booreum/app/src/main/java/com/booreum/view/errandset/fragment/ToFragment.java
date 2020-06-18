package com.booreum.view.errandset.fragment;

import android.content.Context;
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

public class ToFragment extends Fragment {

    public static String toWhere="";
    public static String toDetail="";

    private static ExpandableListView expandableListView;
    private WhereListAdapter adapter;
    Context context;

    public ToFragment(Context context) {
        this.context = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.to_listview);

        adapter = new WhereListAdapter(context, false);
        expandableListView.setAdapter(adapter);

        // 리스트뷰 그룹(부모)뷰를 클릭 했을 경우
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                toWhere = Building.building[groupPosition];
                Log.d("Errand_", "그룹클릭리스너 : " + groupPosition);

                return false;
            }
        });

        // 그룹이 열릴 경우 이벤트 발
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        View header = getLayoutInflater().inflate(R.layout.listview_header, null, false);
        TextView tv = header.findViewById(R.id.list_header_title);
        tv.setText("어디로");
        expandableListView.addHeaderView(header);

        return view;
    }

    public static void collapseAllGroup(){
        for(int i=0; i< Building.building.length; i++)
            expandableListView.collapseGroup(i);
    }

}