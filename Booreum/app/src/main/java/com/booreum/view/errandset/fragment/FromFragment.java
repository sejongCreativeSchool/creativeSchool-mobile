package com.booreum.view.errandset.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.SimpleExpandableListAdapter;

import com.booreum.Constant.Building;
import com.booreum.adapter.FromListAdapter;
import com.booreum.adapter.ListViewAdapter;
import com.booreum.booreum.R;

public class FromFragment extends Fragment {

    public static String fromWhere;
    public static String fromDetail;

    private ExpandableListView expandableListView;
    private FromListAdapter adapter;
    private Context context;

    public FromFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_from, container, false);

        expandableListView = (ExpandableListView)view.findViewById(R.id.from_listview);

        adapter = new FromListAdapter(context);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                fromWhere = Building.building[groupPosition];
                return false;
            }
        });

        return view;
    }
}