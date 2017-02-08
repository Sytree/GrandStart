package com.example.huii.grandstart.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.huii.grandstart.app.GsApplication;
import com.example.huii.grandstart.app.di.GsAppComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements WithDi {
    protected GsAppComponent gsAppComponent;
    protected View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getAppComponent();
    }

    protected String getResourceString(int key) {
        return getResources().getString(key);
    }

    @Override
    public void getAppComponent() {
        this.gsAppComponent = ((GsApplication)getApplication()).getComponent();
        setupDi();
    }

    public View getRootView() {
        return this.rootView;
    }
}
