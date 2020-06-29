package com.booreum.view.main.fragment.setting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.booreum.Constant.PreferenceManager;
import com.booreum.adapter.ListViewAdapter;
import com.booreum.booreum.R;
import com.booreum.view.main.I_MainView;
import com.booreum.view.main.MainActivity;
import com.booreum.view.main.MainPresenter;
import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements I_SettingView, View.OnClickListener{

    static final String[] LIST_MENU = {"이용약관", "앱 리뷰", "공지사항"} ;

    private Context context;
    private I_SettingPresenter i_settingPresenter;
    private ListView listView;
    private View view;
    private Button statusChangeButton, settingNoticeButton, logoutButton;
    private ImageView profile;
    private TextView status_textview, settingName;

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
        settingNoticeButton = (Button)view.findViewById(R.id.setting_notice_button);
        logoutButton = (Button)view.findViewById(R.id.setting_logout_button);
        status_textview = (TextView)view.findViewById(R.id.setting_now_status_textView);
        profile = (ImageView)view.findViewById(R.id.setting_profile);
        settingName = (TextView)view.findViewById(R.id.setting_name);
        Glide.with(getContext()).load(getResources().getDrawable(R.drawable.icon_needer_profile)).circleCrop().into(profile);

        if(PreferenceManager.isHelper(getContext())) {
            statusChangeButton.setText("니더로 전환");
            status_textview.setText("헬퍼");
        }

        settingName.setText(MainPresenter.user.getName());

        setListViewAdapter();
        setListenter();
    }

    private void setListenter() {
        statusChangeButton.setOnClickListener(this);
        settingNoticeButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
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
            case R.id.setting_notice_button:
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                String packageName = "com.booreum.booreum";
                intent.setData(Uri.parse("package:" + packageName));
                context.startActivity(intent);
                break;
            case R.id.setting_logout_button:
                i_settingPresenter.doLogOut();
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
