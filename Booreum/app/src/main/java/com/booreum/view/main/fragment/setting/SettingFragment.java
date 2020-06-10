package com.booreum.view.main.fragment.setting;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.booreum.adapter.ListViewAdapter;
import com.booreum.booreum.R;
import com.booreum.view.main.I_MainView;
import com.booreum.view.main.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements I_SettingView, View.OnClickListener{

    static final String[] LIST_MENU = {"이용약관", "앱 리뷰", "고객센터", "공지사항"} ;

    private Context context;
    private I_SettingPresenter i_settingPresenter;
    private ListView listView;
    private View view;
    private Button statusChangeButton;

    public SettingFragment(Context context) {
        this.context = context;
        i_settingPresenter = new SettingPresenter(this.context, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        initView();

        return view;
    }

    private void initView() {
        listView = (ListView)view.findViewById(R.id.setting_listView);
        statusChangeButton = (Button)view.findViewById(R.id.setting_change_status_button);



        setListViewAdapter();
        setListenter();
    }

    private void setListenter() {
        statusChangeButton.setOnClickListener(this);
    }

    private void setListViewAdapter() {
        ListViewAdapter adapter = new ListViewAdapter() ;
        for(String str : LIST_MENU){
            adapter.addItem(str);
        }
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_change_status_button:
                i_settingPresenter.doChangeStatus();
                break;
        }
    }

    @Override
    public void onReStart() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(SettingFragment.this).commit();
        fragmentManager.popBackStack();

        getActivity().recreate();
    }
}
