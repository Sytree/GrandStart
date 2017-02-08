package com.example.huii.grandstart.ui.mainpage.di;

import com.example.huii.grandstart.ui.mainpage.MainPageContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huii on 17/1/16.
 */
@Module
public class MainPageModule {
    MainPageContract.View view;

    public MainPageModule(MainPageContract.View view) {
        this.view = view;
    }

    @Provides
    MainPageContract.View provideMainPageView() {
        return this.view;
    }
}
