package com.example.huii.grandstart.ui.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.huii.grandstart.R;
import com.example.huii.grandstart.ui.base.BaseActivity;
import com.example.huii.grandstart.ui.custom.customedit.CustomEditActivity;
import com.example.huii.grandstart.ui.mainpage.di.DaggerMainPageComponent;
import com.example.huii.grandstart.ui.mainpage.di.MainPageComponent;
import com.example.huii.grandstart.ui.mainpage.di.MainPageModule;
import com.example.huii.grandstart.ui.uiutil.GsAnimatorProvider;
import com.example.huii.grandstart.ui.views.GsClockView;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainPageContract.View{

    MainPageContract.Presenter presenter;
    GsAnimatorProvider animatorProvider;
    MainPageComponent component;

    @Bind(R.id.main_clockView)
    GsClockView mClockView;
    @Bind(R.id.main_content_scroll)
    ScrollView mSrollView;

    //工作
    @Bind(R.id.main_work)
    RelativeLayout mWorkContainer;
    @Bind(R.id.main_work_count)
    TextView mWorkCount;
    @Bind(R.id.main_work_expand)
    ImageView mWorkExpand;
    @Bind(R.id.main_work_list)
    RecyclerView mWorkList;
    @Bind(R.id.main_work_add)
    ImageView mWorkAdd;

    //日常
    @Bind(R.id.main_custom)
    RelativeLayout mCustomContainer;
    @Bind(R.id.main_custom_count)
    TextView mCustomCount;
    @Bind(R.id.main_custom_finished_value)
    TextView mCustomFinishedValue;
    @Bind(R.id.main_custom_unfinished_value)
    TextView getmCustomUnfinishedValue;
    @Bind(R.id.main_custom_expand)
    ImageView mCustomExpand;
    @Bind(R.id.main_custom_list)
    RecyclerView mCustomList;
    @Bind(R.id.main_custom_add)
    ImageView mCustomAdd;

    //待办
    @Bind(R.id.main_plan)
    RelativeLayout mPlanContainer;
    @Bind(R.id.main_plan_count)
    TextView mPlanCount;
    @Bind(R.id.main_plan_finished_value)
    TextView mPlanFinishedValue;
    @Bind(R.id.main_plan_unfinished_value)
    TextView mPlanUnfinishedValue;
    @Bind(R.id.main_plan_expand)
    ImageView mPlanExpand;
    @Bind(R.id.main_plan_list)
    RecyclerView mPlanList;
    @Bind(R.id.main_plan_add)
    ImageView mPlanAdd;

    private final String WORK_COUNT_LABLE = "个工作流";
    private final String CUSTOM_COUNT_LABLE = "个日常";
    private final String PLAN_COUNT_LABLE = "个代办";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @OnClick(R.id.main_work_expand)
    public void onWorkExpandClick() {
        presenter.onWorkExpend();
    }

    @Override
    public void setupDi() {
        component = DaggerMainPageComponent.builder().gsAppComponent(gsAppComponent).mainPageModule(new MainPageModule(this)).build();
        presenter = component.getPresenter();
    }

    @Override
    public void updateTime() {
        mClockView.updateTime();
    }

    @Override
    public void setStateStr(String stateStr) {
        mClockView.setStateStr(stateStr);
    }

    @Override
    public void setPlan(String planStr, long planTime) {
        mClockView.setPlan(planTime,planStr);
    }

    @Override
    public void setWorkCount(int count) {
        mWorkCount.setText(count + WORK_COUNT_LABLE);
    }

    @Override
    public void setWorkList() {
        // TODO: 17/1/16
    }

    @OnClick(R.id.main_custom_expand)
    public void onCustomExpandClick() {
        Intent i = new Intent(this, CustomEditActivity.class);
        startActivity(i);
    }


    //展开,工作
    @Override
    public void expandWork() {
        mWorkList.setVisibility(View.VISIBLE);
        mWorkExpand.setImageResource(R.mipmap.ic_collect_green);
        mWorkAdd.setVisibility(View.VISIBLE);
        animatorProvider.providContainerSelectedAnimator(mWorkContainer, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void setWorkExpandable(boolean expandable) {
        if (expandable) {
            mWorkExpand.setImageResource(R.mipmap.expand);
        } else {
            mWorkExpand.setImageResource(R.mipmap.ic_collect_green);
        }
    }

    //收起 工作
    @Override
    public void collectWork() {
        mWorkList.setVisibility(View.VISIBLE);
        mWorkExpand.setImageResource(R.mipmap.ic_collect_green);
        mWorkAdd.setVisibility(View.VISIBLE);
        animatorProvider.providContainerSelectedAnimator(mWorkContainer, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void setCustomCount(int count) {
        mCustomCount.setText(count + CUSTOM_COUNT_LABLE);
    }

    @Override
    public void setCustomList() {
        // TODO: 17/1/16
    }

    @Override
    public void setCustomFinishedCount(int count) {
        mCustomFinishedValue.setText(count + "");
    }

    @Override
    public void setCustomUnfinishedCount(int count) {
        getmCustomUnfinishedValue.setText(count + "");
    }

    @Override
    public void expandCustom() {
        mCustomList.setVisibility(View.VISIBLE);
        mCustomExpand.setImageResource(R.mipmap.ic_collect_green);
        mCustomAdd.setVisibility(View.VISIBLE);
        animatorProvider.providContainerSelectedAnimator(mCustomContainer, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void setCustomExpandable(boolean expandable) {
        if (expandable) {
            mCustomExpand.setImageResource(R.mipmap.expand);
        } else {
            mCustomExpand.setImageResource(R.mipmap.ic_collect_green);
        }
    }

    @Override
    public void collectCustom() {
        mCustomList.setVisibility(View.VISIBLE);
        mCustomExpand.setImageResource(R.mipmap.ic_collect_green);
        mCustomAdd.setVisibility(View.VISIBLE);
        animatorProvider.providContainerSelectedAnimator(mCustomContainer, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void setPlanCount(int count) {
        mPlanCount.setText(count + PLAN_COUNT_LABLE);
    }

    @Override
    public void setPlanList() {
        // TODO: 17/1/16
    }

    @Override
    public void setPlanFinishedCount(int count) {
        mPlanFinishedValue.setText(count + "");
    }

    @Override
    public void setPlanUnfinishedCount(int count) {
        mPlanUnfinishedValue.setText(count + "");
    }

    @Override
    public void expandPlan() {
        mPlanList.setVisibility(View.VISIBLE);
        mPlanExpand.setImageResource(R.mipmap.ic_collect_green);
        mPlanAdd.setVisibility(View.VISIBLE);
        animatorProvider.providContainerSelectedAnimator(mPlanContainer, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void setPlanExpandable(boolean expandable) {
        if (expandable) {
            mPlanExpand.setImageResource(R.mipmap.expand);
        } else {
            mPlanExpand.setImageResource(R.mipmap.ic_collect_green);
        }
    }

    @Override
    public void collectPlan() {
        mPlanList.setVisibility(View.VISIBLE);
        mPlanExpand.setImageResource(R.mipmap.ic_collect_green);
        mPlanAdd.setVisibility(View.VISIBLE);
        animatorProvider.providContainerSelectedAnimator(mPlanContainer, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public BaseActivity getActivity() {
        return this;
    }
}
