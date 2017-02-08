package com.example.huii.grandstart.ui.mainpage;

import com.example.huii.grandstart.ui.base.BasePresenter;
import com.example.huii.grandstart.ui.base.BaseView;

/**
 * Created by huii on 17/1/11.
 */
public class MainPageContract {
    public static interface View extends BaseView {
        void updateTime();
        void setStateStr(String stateStr);
        void setPlan(String planStr,long planTime);

        void setWorkCount(int count);
        void setWorkList();
        void expandWork();
        void setWorkExpandable(boolean expandable);
        void collectWork();

        void setCustomCount(int count);
        void setCustomList();
        void setCustomFinishedCount(int count);
        void setCustomUnfinishedCount(int count);
        void expandCustom();
        void setCustomExpandable(boolean expandable);
        void collectCustom();

        void setPlanCount(int count);
        void setPlanList();
        void setPlanFinishedCount(int count);
        void setPlanUnfinishedCount(int count);
        void expandPlan();
        void setPlanExpandable(boolean expandable);
        void collectPlan();
    }

    public static interface Presenter extends BasePresenter {
        void addWork();
        void turnToWork();
        void onWorkExpend();

        void addCustom();
        void turnToCustom();

        void addPlan();
        void turnToPlan();
    }
}
