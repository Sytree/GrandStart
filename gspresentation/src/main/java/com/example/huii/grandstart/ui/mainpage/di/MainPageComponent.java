package com.example.huii.grandstart.ui.mainpage.di;

import com.example.huii.grandstart.app.di.GsAppComponent;
import com.example.huii.grandstart.di.PerActivity;
import com.example.huii.grandstart.ui.base.BaseActivity;
import com.example.huii.grandstart.ui.mainpage.MainPagePresenterImp;
import com.example.huii.grandstart.ui.startup.StartupPresenterImp;

import dagger.Component;

/**
 * Created by huii on 17/1/16.
 */
@PerActivity
@Component(
        dependencies = GsAppComponent.class,
        modules = MainPageModule.class
)
public interface MainPageComponent {
    void inject(BaseActivity activity);
    MainPagePresenterImp getPresenter();
}
