package com.example.huii.grandstart.ui.startup.di;

import com.example.huii.grandstart.ui.startup.StartupContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huii on 17/1/10.
 */
@Module
public class StartupModule {
    StartupContract.View view;

    public StartupModule(StartupContract.View view) {
        this.view = view;
    }

    @Provides
    StartupContract.View provideStartupView() {
        return view;
    }
}
