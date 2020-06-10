package com.booreum.view.main.fragment.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.booreum.Constant.PreferenceManager;
import com.booreum.booreum.R;
import com.booreum.model.User;
import com.booreum.view.main.MainPresenter;

class SettingPresenter implements I_SettingPresenter, DialogInterface.OnClickListener {

    private Context context;
    private I_SettingView i_settingView;
    private User user;

    public SettingPresenter(Context context, I_SettingView i_settingView) {
        this.context = context;
        this.i_settingView = i_settingView;
        this.user = MainPresenter.user;
    }

    @Override
    public void doChangeStatus() {
        Boolean helper = user.getHelper();
        if(! helper){
            //헬퍼 인증을 받지 않았으면,
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
            builder.setTitle("헬퍼로 등록되어 있지 않습니다.").setMessage("헬퍼로 등록하시겠습니까?");

            builder
                    .setPositiveButton("네", this)
                    .setNegativeButton("아니오", this)
                    .show();
        }
        else{

            //헬퍼인증을 받은 경우
            String nowState = PreferenceManager.getString(context, PreferenceManager.KEY_STATUS_CHANGE);
            if(nowState.equals(PreferenceManager.NEEDER)){
                i_settingView.onReStart();

                //지금이 니더면
                Log.d("SettingFragment", "now state : " + nowState);
                PreferenceManager.setString(context, PreferenceManager.KEY_STATUS_CHANGE, PreferenceManager.HELPER);
                nowState = PreferenceManager.getString(context, PreferenceManager.KEY_STATUS_CHANGE);
                Log.d("SettingFragment", "now state : " + nowState);
            }
            else if(nowState.equals(PreferenceManager.HELPER)){
                i_settingView.onReStart();

                //지금이 헬퍼면
                Log.d("SettingFragment", "now state : " + nowState);
                PreferenceManager.setString(context, PreferenceManager.KEY_STATUS_CHANGE, PreferenceManager.NEEDER);
                nowState = PreferenceManager.getString(context, PreferenceManager.KEY_STATUS_CHANGE);
                Log.d("SettingFragment", "now state : " + nowState);

            }
        }
    }

    //다이얼로그
    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case -1: //긍정


                break;
            case -2://부정
                break;
        }
    }


}
