package com.example.huii.grandstart.ui.startup.di;

import com.example.huii.grandstart.app.di.GsAppComponent;
import com.example.huii.grandstart.di.PerActivity;
import com.example.huii.grandstart.ui.base.BaseActivity;
import com.example.huii.grandstart.ui.startup.StartupContract;
import com.example.huii.grandstart.ui.startup.StartupPresenterImp;

import dagger.Component;

/**
 * Created by huii on 17/1/10.
 */
@PerActivity
@Component(
        dependencies = GsAppComponent.class,
        modules = StartupModule.class
)
public interface StartupComponent {
    void inject(BaseActivity activity);
    StartupPresenterImp getPresenter();
}
