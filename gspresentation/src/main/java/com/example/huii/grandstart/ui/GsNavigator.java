package com.example.huii.grandstart.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.huii.grandstart.ui.base.BaseActivity;
import com.example.huii.grandstart.ui.mainpage.MainActivity;

/**
 * Created by huii on 17/1/8.
 */
public class GsNavigator {


    /**
     * 首页到主页的跳转动画
     *
     * @param acContext
     * @param transitView
     * @param triggerView
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void getToMainPage(final BaseActivity acContext, final View transitView, final View triggerView) {
        final float begainX = triggerView.getX();
        final float begainY = triggerView.getY();

        //动画开始坐标 屏幕中心
        WindowManager windowManager = (WindowManager) acContext.getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);

        int centerX = outMetrics.widthPixels / 2;
        int centerY = outMetrics.heightPixels / 2;

        Log.d("animator",centerX + "=======" + centerY);

        AnimatorSet transitAnimatorSet = new AnimatorSet();

        Animator transitAnim = getTransitAnimator(transitView,centerX,centerY);
        Animator triggerAnim = getTriggerAnimator(triggerView,centerX,centerY);
        Animator triggerAcAnim = getTriggerActionAnimator(triggerView);

        //动画顺序
        transitAnimatorSet.play(transitAnim)
                .after(triggerAnim);
        transitAnimatorSet.play(triggerAnim)
                .after(triggerAcAnim);

        transitAnimatorSet.start();

        transitAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //还原
//                transitView.setVisibility(View.GONE);
                triggerView.setX(begainX);
                triggerView.setY(begainY);
                triggerView.setElevation(0);

                Intent i = new Intent(acContext, MainActivity.class);
                acContext.startActivity(i);
                acContext.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 按钮上浮动画
     *
     * @param triggerView
     * @return
     */
    public static Animator getTriggerActionAnimator(final View triggerView) {
        ValueAnimator triggerAnimator = ValueAnimator.ofFloat(0f,3f);
        triggerAnimator.setDuration(200);
        triggerAnimator.setTarget(triggerView);
        triggerAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        triggerAnimator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                Float value = Float.valueOf(3 * fraction * 5);
                return value;
            }
        });

        triggerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                triggerView.setElevation(value);
            }
        });

        return triggerAnimator;
    }

    /**
     * 按钮移动动画
     *
     * @param triggerView
     * @param tagetX
     * @param targetY
     * @return
     */
    public static Animator getTriggerAnimator(final View triggerView, final int tagetX, final int targetY) {
        ValueAnimator triggerAnimator = new ValueAnimator();
        final float startX = triggerView.getX();
        final float startY = triggerView.getY();

        final float endX = tagetX - (triggerView.getWidth() / 2);
        final float endY = targetY - (triggerView.getHeight())/2;

        Log.d("trigger" , startX + "   " + endX + "+++++" + startY + "   " + endY);

        triggerAnimator.setObjectValues(new PointF(startX,startY)
                ,new PointF(endX,endY));
        triggerAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        triggerAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            //fraction 动画完成程度 类似百分比
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF();
                float Vx = (endX - startX)*fraction;
                point.x = startX + Vx;
                float Vy =(endY - startY)*fraction;
                point.y = startY + Vy;
                return point;
            }
        });
        triggerAnimator.setDuration(400);
//        triggerAnimator.start();
        triggerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                triggerView.setX(point.x);
                triggerView.setY(point.y);
            }
        });

        return triggerAnimator;
    }

    /**
     * 揭露动画
     * 绿色
     * @param transitView
     * @param startX
     * @param startY
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator getTransitAnimator(final View transitView, int startX, int startY) {
        float finalRadius = (float) Math.hypot((double) startX, (double) startY);

        Log.d("transit",startX + "+++++++" + startY);

        // 定义揭露动画
        Animator transitAnimator = ViewAnimationUtils.createCircularReveal(
                transitView, startX, startY, 0, finalRadius);
        transitAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        transitAnimator.setDuration(800);

        transitAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                transitView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        return transitAnimator;
    }


}
