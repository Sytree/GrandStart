package com.example.huii.grandstart.ui.startup;

import com.example.huii.grandstart.ui.base.BasePresenter;
import com.example.huii.grandstart.ui.base.BaseView;

/**
 * Created by huii on 17/1/9.
 */
public class StartupContract {
    public static interface View extends BaseView {
        void setYear(String year);
        void setMonth(String month);
        void setDay(String day);
        void showTips();
        void showButton();
    }

    public static interface Presenter extends BasePresenter {
        void setDate();
        void changeDate();
        void getReadyToGo();
        void goToNext();
    }
}
