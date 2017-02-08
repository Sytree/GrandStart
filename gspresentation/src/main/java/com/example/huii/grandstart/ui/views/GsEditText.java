package com.example.huii.grandstart.ui.views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.huii.grandstart.R;
import com.example.huii.grandstart.ui.uiutil.GsDimensionUtil;

/**
 * Created by huii on 17/1/16.
 */
public class GsEditText extends AppCompatEditText {

    Context mContex;

    private ObjectAnimator labelFocusAnimator;
    private float focusFaction;
    boolean isHasFocus;

    private String labelStr;
    private int labelSize = GsDimensionUtil.sp2px(24);
    private int labelColor;

    private int basePadingTop;

    private Paint mPaint;

    public GsEditText(Context context) {
        super(context);
        init(context,null);
    }

    public GsEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public GsEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void init(Context context, AttributeSet attrs) {

        mContex = context;

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.GsEditText);

        labelStr = ta.getString(R.styleable.GsEditText_floatLabel_text);
        int size = ta.getInt(R.styleable.GsEditText_floatLabel_textSize,24);
        labelSize = GsDimensionUtil.sp2px(size);
        labelColor = ta.getColor(R.styleable.GsEditText_underLine_color
                ,context.getColor(R.color.gsDevider));

        if (TextUtils.isEmpty(labelStr)) {
            labelStr = getHint().toString();
        }

        mPaint = new Paint();
        super.setOnFocusChangeListener(onFocusChangeListener);
    }

    OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                isHasFocus = hasFocus;
            } else {

            }
        }
    };

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {

        //画lable
        if(!TextUtils.isEmpty(labelStr) && hasFocus()) {
            mPaint.setTextSize(labelSize);
            mPaint.setColor(mContex.getColor(R.color.gsMainDraw));
            mPaint.setStyle(Paint.Style.FILL);

            int labelStartX = getPaddingLeft();
//            if ((getGravity() & Gravity.RIGHT) == Gravity.RIGHT || isRTL()) {
//                labelStartX =
//            }
            float labelStartY = basePadingTop - getTextTop(mPaint);
            canvas.drawText(labelStr,labelStartX,labelStartY,mPaint);
        }

        super.onDraw(canvas);
    }

    private boolean isRTL() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return false;
        }
        Configuration config = getResources().getConfiguration();
        return config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }

    private ObjectAnimator getAnimator() {
        if (labelFocusAnimator == null) {
            labelFocusAnimator = ObjectAnimator.ofFloat(this,"focusFaction",0f,1f);
        }
        labelFocusAnimator.setDuration(300);
        labelFocusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                adjustPading((int) (labelSize * value));
            }
        });
        labelFocusAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                clearHint();
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
        return labelFocusAnimator;
    }
    private void clearHint() {
        super.setHint(null);
    }

    private void adjustPading(int paddingTop) {
        super.setPadding(getPaddingLeft(),paddingTop,getPaddingRight(),getPaddingBottom());
    }

    private float getTextTop(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.top;
    }
}
