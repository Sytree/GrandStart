package com.example.huii.grandstart.logic;

import com.example.executor.PostExecutionThread;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by huii on 17/1/10.
 */
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
