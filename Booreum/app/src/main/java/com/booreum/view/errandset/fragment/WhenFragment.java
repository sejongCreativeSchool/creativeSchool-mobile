package com.booreum.view.errandset.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.booreum.Constant.Building;
import com.booreum.adapter.WhenListAdapter;
import com.booreum.booreum.R;

public class WhenFragment extends Fragment {

    public static String year="";
    public static String month="";
    public static String day="";

    public static ExpandableListView expandableListView;
    WhenListAdapter adapter;
    Context context;

    public WhenFragment(Context context) {
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_when, container, false);

        expandableListView = (ExpandableListView)view.findViewById(R.id.when_listview);
        adapter = new WhenListAdapter(context);
        expandableListView.setAdapter(adapter);

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

        setListViewPosition();

        return view;
    }

    public static void setListViewPosition(){
        expandableListView.expandGroup(0);
    }

    public static void collapseAllGroup(){
        for(int i = 0; i< Building.building.length; i++)
            expandableListView.collapseGroup(i);
    }

}