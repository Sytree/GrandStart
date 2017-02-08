package com.example.huii.grandstart.app;

import android.app.Application;

import com.example.huii.grandstart.app.di.DaggerGsAppComponent;
import com.example.huii.grandstart.app.di.GsAppComponent;
import com.example.huii.grandstart.app.di.GsAppModule;

/**
 * Created by huii on 17/1/10.
 */
public class GsApplication extends Application {
    GsAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDi();
    }

    private void setupDi() {
        component = DaggerGsAppComponent.builder().gsAppModule(new GsAppModule(this)).build();
    }

    public GsAppComponent getComponent() {
        return component;
    }
}
