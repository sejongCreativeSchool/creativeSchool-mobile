package com.booreum.view.main.fragment.list;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.booreum.Constant.HideKeyboard;
import com.booreum.adapter.HelperListAdapter;
import com.booreum.booreum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements I_ListView {

    Context context;
    View view;
    I_ListPresenter i_listPresenter;
    ExpandableListView expandableListView;
    LinearLayout progressLayout;
    HelperListAdapter adapter;

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

        return view;
    }

    private void initView() {
        i_listPresenter = new ListPresenter(context);
        adapter = new HelperListAdapter(context);
        expandableListView = (ExpandableListView)view.findViewById(R.id.list_errandList);
        progressLayout = (LinearLayout)view.findViewById(R.id.list_progress);

        expandableListView.setAdapter(adapter);
    }

    @Override
    public void setProgress(int visibility) {
        progressLayout.setVisibility(visibility);
        switch (visibility){
            case View.VISIBLE :
                progressLayout.setVisibility(View.INVISIBLE);
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                break;
            case View.GONE : case View.INVISIBLE :
                progressLayout.setVisibility(View.VISIBLE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                break;
        }
    }

    @Override
    public void setData() {
        /**
         * 데이터 가져와서 어뎁터에 추가해주고*/
        adapter.notifyDataSetChanged();
    }

}
