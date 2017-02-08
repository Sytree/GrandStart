package com.example.huii.grandstart.ui.startup;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huii.grandstart.R;
import com.example.huii.grandstart.ui.GsNavigator;
import com.example.huii.grandstart.ui.base.BaseActivity;
import com.example.huii.grandstart.ui.startup.di.DaggerStartupComponent;
import com.example.huii.grandstart.ui.startup.di.StartupComponent;
import com.example.huii.grandstart.ui.startup.di.StartupModule;
import com.example.huii.grandstart.ui.uiutil.GsResourceKey;

import butterknife.Bind;

public class StartupActivity extends BaseActivity implements StartupContract.View {
    StartupComponent component;
    Context acContext;
    BaseActivity mAc;

    StartupContract.Presenter presenter;

    @Bind(R.id.ac_startup_year)
    TextView yearTv;
    @Bind(R.id.ac_startup_month)
    TextView monthTv;
    @Bind(R.id.ac_startup_day)
    TextView dayTv;
    @Bind(R.id.ac_startup_operation_go_text)
    TextView goTv;
    @Bind(R.id.ac_startup_operation_go_button)
    ImageView goBtn;
    @Bind(R.id.transitView_operationGreen_fullScreen)
    LinearLayout startupTransitView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_startup);
        acContext = this;
        mAc = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setYear(String year) {
        yearTv.setText(year + getResourceString(GsResourceKey.YEAR_LABLE));
    }

    @Override
    public void setMonth(String month) {
        monthTv.setText(month + getResourceString(GsResourceKey.MONTH_LABLE));
    }

    @Override
    public void setDay(String day) {
        dayTv.setText(day + getResourceString(GsResourceKey.DAY_LABLE));
    }

    @Override
    public void showTips() {

    }

    @Override
    public void showButton() {
        setGoBtnWaiting(goBtn);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                GsNavigator.getToMainPage(mAc,startupTransitView,v);
            }
        });
    }

    private void setGoBtnWaiting(View view) {

    }

    private void startGoBtnAction(View view) {

    }

    @Override
    public void setupDi() {
        component = DaggerStartupComponent.builder()
                .gsAppComponent(gsAppComponent).startupModule(new StartupModule(this))
                .build();
        component.inject(this);
        presenter = component.getPresenter();
    }

    @Override
    public BaseActivity getActivity() {
        return this;
    }
}
