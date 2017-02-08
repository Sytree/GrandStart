package com.example.huii.grandstart.app.di;

import android.content.Context;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.huii.grandstart.ui.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by huii on 17/1/10.
 */
@Singleton
@Component(
        modules = GsAppModule.class
)
public interface GsAppComponent {
    void inject(BaseActivity activity);
    Context context();
    ThreadExecutor threadExcutor();
    PostExecutionThread postExecutionThread();
}
