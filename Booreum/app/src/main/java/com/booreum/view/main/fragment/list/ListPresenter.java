package com.booreum.view.main.fragment.list;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.booreum.Constant.GitHubServiceProvider;
import com.booreum.model.ErrandResults;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenter implements I_ListPresenter {

    Context context;
    I_ListView i_listView;

    public  static ErrandResults errandResults;

    public ListPresenter(Context context) {
        this.context = context;
        i_listView = new ListFragment();
    }

    @Override
    public void loadList() {
        //i_listView.setProgress(View.VISIBLE);

        GitHubServiceProvider.retrofit.loadErrands()
                .enqueue(new Callback<ErrandResults>() {
                    @Override
                    public void onResponse(Call<ErrandResults> call, Response<ErrandResults> response) {
                        if(!response.isSuccessful()){
                            retrofitFailed();
                        }

                       retrofitSuccess(response);
                    }

                    @Override
                    public void onFailure(Call<ErrandResults> call, Throwable t) {
                        retrofitFailed();
                    }
                });

    }

    void retrofitSuccess(Response<ErrandResults> response){
        errandResults = response.body();
        i_listView.setData(errandResults);
        //ListFragment.adapter.setResults();
    }

    void retrofitFailed(){
        Log.d("ListPresenter", "retrofit failed");
        Toast.makeText( context.getApplicationContext(), "서버 통신 실패", Toast.LENGTH_SHORT ).show();
    }

}
