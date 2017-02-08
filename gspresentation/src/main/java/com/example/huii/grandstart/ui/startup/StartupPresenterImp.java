package com.example.huii.grandstart.ui.startup;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.huii.grandstart.R;
import com.example.huii.grandstart.di.PerActivity;
import com.example.huii.grandstart.ui.uiutil.GsDateUtil;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by huii on 17/1/10.
 */
public class StartupPresenterImp implements StartupContract.Presenter {
    StartupContract.View view;

    @PerActivity
    @Inject
    public StartupPresenterImp(StartupContract.View view) {
        this.view = view;
    }

    @Override
    public void setDate() {
        view.setYear(GsDateUtil.getYear());
        view.setMonth(GsDateUtil.getMonth());
        view.setDay(GsDateUtil.getDay());
    }

    @Override
    public void changeDate() {

    }

    @Override
    public void getReadyToGo() {

    }

    @Override
    public void goToNext() {

    }

    @Override
    public void start() {
        setStatusBarColor();
        setDate();
        view.showButton();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setStatusBarColor() {
        Window window = view.getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(view.getActivity().getResources()
                .getColor(R.color.gsMainDraw,null));
    }

}
