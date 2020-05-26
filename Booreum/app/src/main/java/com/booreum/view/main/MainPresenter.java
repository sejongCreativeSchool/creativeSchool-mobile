package com.booreum.view.main;

public class MainPresenter implements I_MainPresenter{

    private I_MainView mainView;

    public MainPresenter(I_MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public String doTabTitle(int position) {
        switch (position) {
            case 0:
                mainView.setTabTitle("카테고리");break;
            case 1:
                mainView.setTabTitle("채팅");break;
            case 2:
                mainView.setTabTitle("설정");break;
        }
        return null;
    }
}
