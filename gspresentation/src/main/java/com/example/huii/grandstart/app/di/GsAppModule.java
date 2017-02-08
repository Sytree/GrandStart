package com.example.huii.grandstart.app.di;

import android.app.Application;
import android.content.Context;

import com.example.executor.GsExecutor;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.huii.grandstart.logic.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huii on 17/1/10.
 */
@Module
public class GsAppModule {
    private final Application mApp;
    private final Context appContext;

    public GsAppModule(Application mApp) {
        this.mApp = mApp;
        this.appContext = mApp.getApplicationContext();
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return appContext;
    }

    @Singleton
    @Provides
    public Application provideApp() {
        return mApp;
    }

    @Singleton
    @Provides
    public ThreadExecutor provideThreadExcutor(GsExecutor gsExecutor) {
        return gsExecutor;
    }

    @Singleton
    @Provides
    public PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}
