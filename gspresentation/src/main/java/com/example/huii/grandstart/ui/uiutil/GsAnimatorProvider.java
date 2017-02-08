package com.example.huii.grandstart.ui.uiutil;

import android.view.View;
import android.view.animation.Animation;

/**
 * Created by huii on 17/1/16.
 */
public interface GsAnimatorProvider {
    void providContainerSelectedAnimator(View container, Animation.AnimationListener listener);
    void providerContainerUnSelectedAnimator(View container, Animation.AnimationListener listener);
}
