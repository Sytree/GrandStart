package com.example.huii.grandstart.ui.mainpage;

import javax.inject.Inject;

/**
 * Created by huii on 17/1/16.
 */
public class MainPagePresenterImp implements MainPageContract.Presenter {
    // TODO: 17/1/16
    MainPageContract.View view;

    @Inject
    public MainPagePresenterImp(MainPageContract.View view) {
        this.view = view;
    }

    @Override
    public void addWork() {

    }

    @Override
    public void turnToWork() {

    }

    @Override
    public void onWorkExpend() {

    }

    @Override
    public void addCustom() {

    }

    @Override
    public void turnToCustom() {

    }

    @Override
    public void addPlan() {

    }

    @Override
    public void turnToPlan() {

    }

    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
