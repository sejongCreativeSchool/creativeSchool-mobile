package com.booreum.view.main.fragment.list;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.booreum.Constant.HideKeyboard;
import com.booreum.adapter.HelperListAdapter;
import com.booreum.booreum.R;
import com.booreum.model.ErrandResults;

public class ListFragment extends Fragment implements I_ListView {

    Context context;
    View view;
    I_ListPresenter i_listPresenter;
    ExpandableListView expandableListView;
   public  static HelperListAdapter adapter;

    public ListFragment() {
    }

    public ListFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        initView();

        i_listPresenter.loadList();

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                expandableListView.collapseGroup(groupPosition);
                Log.d("ttt", "col : "+groupPosition);
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

        return view;
    }

    private void initView() {
        i_listPresenter = new ListPresenter(context);
        adapter = new HelperListAdapter(context);
        expandableListView = (ExpandableListView)view.findViewById(R.id.list_errandList);


        expandableListView.setAdapter(adapter);


    }

    @Override
    public void setData(ErrandResults results) {
        Log.d("ListPresenter", "responst : " +results.getData().get(0).getDesc());
        //setAdapterData(results);
        adapter.setResults(results);
        adapter.notifyDataSetChanged();
    }

}
