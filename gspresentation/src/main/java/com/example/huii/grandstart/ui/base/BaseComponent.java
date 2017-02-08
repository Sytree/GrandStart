package com.example.huii.grandstart.ui.base;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import butterknife.ButterKnife;

/**
 * Created by huii on 17/1/9.
 */
public abstract class BaseComponent<T> {
    public abstract void draw(T param);
    public abstract void show();
    public abstract void hide();
    public abstract void change(T param);
    public abstract void action();
    public abstract void destroy();

    public static class ViewHolder {

    }

}
