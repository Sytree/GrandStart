package com.example.huii.grandstart.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.huii.grandstart.R;
import com.example.huii.grandstart.ui.uiutil.GsDimensionUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huii on 17/1/11.
 */
public class GsClockView extends View {

    private Context mContext;
    private Paint mPaint;
    private TimerListener listener;
    private SimpleDateFormat dateFormat;

    //计划的时间
    private long planTime;
    private long planAddTime;
    private float planTimeLeftLength;
    private String planTimeStr = "12:00";
    private long currTime;
    private String currTimeStr = "8:00";

    private long timeUnit = 60000;
    //计划描述
    private String planStr = "去和马总吃饭";
    private float planStrSize = GsDimensionUtil.sp2px(16);
    private float planStrBottom;
    //状态描述
    private String stateStr = "空闲";
    private float stateStrSize = GsDimensionUtil.sp2px(48);
    private float stateStrBottom;

    private int viewWidth;
    private int viewHeight;

    private int timeLineStrokeWidth;

    private int timeLineY;

    private int leftTimeLineStartX;
    private float leftTimeLineEndX;

    //画时钟
    private float clockRadius;
    private String clockTimeStr = currTimeStr;
    private float clockTimeStrSize;

    private float clockCX;
    private float clockCY;

    private float clockTimeStrWidth;
    private float clockTimeStrHeight;

    private float clockDashLineBottom;



    private int timeLineScaleRadius;
    private float timeLineScaleBottom;

    private float timeLineScaleCX;
    private float timeLineScaleCY;

    private float rightTimeLineStartX;
    private float rightTimeLineEndX;
    private float rightTimeLineLength;

    private float dashLineWidth = GsDimensionUtil.dp2px(2);

    public GsClockView(Context context) {
        this(context,null);
    }

    public GsClockView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public GsClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.GsClockView);

        int stateStrSizeSp = typedArray.getInt(R.styleable.GsClockView_state_textSize,48);
        stateStrSize = GsDimensionUtil.sp2px(stateStrSizeSp);
        int planStrSizeSp = typedArray.getInt(R.styleable.GsClockView_plan_textSize,16);
        planStrSize = GsDimensionUtil.sp2px(planStrSizeSp);

        mContext = context;
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        //
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        dateFormat = new SimpleDateFormat("HH:mm");

        timeLineStrokeWidth = GsDimensionUtil.dp2px(3);
        clockRadius = GsDimensionUtil.dp2px(32) - timeLineStrokeWidth;
        timeLineY = (int) (getY() + clockRadius) + timeLineStrokeWidth;
        planTimeLeftLength = clockRadius * 2;
        timeLineScaleRadius = GsDimensionUtil.dp2px(8);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;

        width = widthSize;

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (timeLineStrokeWidth * 2 + clockRadius * 2 + timeLineScaleRadius
                    + GsDimensionUtil.dp2px(4) * 2 + planStrSize + GsDimensionUtil.dp2px(16)
                    + stateStrSize + GsDimensionUtil.dp2px(8) * 3 + GsDimensionUtil.dp2px(32))
                    + getPaddingBottom() + getPaddingTop();
        }

        setMeasuredDimension(width,height);


    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        drawLeftTimeLine(canvas);

        drawClock(canvas);

        drawRightTimeLine(canvas);
        drawTimeLineScale(canvas);
        drawTimeLineScaleDashLine(canvas,clockRadius);
        drawPlanStr(canvas);

        drawState(canvas);
    }

    private void drawLeftTimeLine(Canvas canvas) {
        drawLeftTimeLine(canvas,(getWidth() / 2) - clockRadius);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void drawLeftTimeLine(Canvas canvas,float length) {
        leftTimeLineStartX = getPaddingLeft();
        leftTimeLineEndX = leftTimeLineStartX + length;

        mPaint.setStrokeWidth(timeLineStrokeWidth);
        mPaint.setColor(mContext.getColor(R.color.gsMainDraw));
        canvas.drawLine(leftTimeLineStartX,timeLineY,leftTimeLineEndX,timeLineY,mPaint);
    }

    //当前时间
    @TargetApi(Build.VERSION_CODES.M)
    private void drawClock(Canvas canvas) {
        mPaint.reset();
        mPaint.setColor(mContext.getColor(R.color.gsMainDraw));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(timeLineStrokeWidth);

        clockCX = getWidth() / 2;
        clockCY = timeLineY;
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(clockCX,clockCY,clockRadius,mPaint);

        clockTimeStrSize = GsDimensionUtil.sp2px(16);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(clockTimeStrSize);

        clockTimeStrWidth = getStringWidth(clockTimeStr);
        clockTimeStrHeight = getStringHeight();

        float textX = clockCX - (clockTimeStrWidth / 2);
        float textY = clockCY - (clockTimeStrHeight / 2);
        canvas.drawText(clockTimeStr,textX,textY,mPaint);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void drawRightTimeLine(Canvas canvas) {
        mPaint.setStrokeWidth(timeLineStrokeWidth);
        mPaint.setColor(mContext.getColor(R.color.gsMainDraw));
        rightTimeLineLength = (getWidth() / 2) - getPaddingRight() - clockRadius;
        rightTimeLineStartX = (getWidth() / 2) + clockRadius;
        rightTimeLineEndX = getWidth() - getPaddingRight();

        canvas.drawLine(rightTimeLineStartX,timeLineY,rightTimeLineEndX,timeLineY,mPaint);
    }


    //时间线上的 游标
    @TargetApi(Build.VERSION_CODES.M)
    private void drawTimeLineScale(Canvas canvas) {
        mPaint.reset();

        //画圈
        float baseX = (getWidth() / 2) + clockRadius + timeLineStrokeWidth * 2
                + timeLineScaleRadius;
        timeLineScaleCX = baseX + planTimeLeftLength;
        timeLineScaleCY = timeLineY;

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mContext.getColor(R.color.gsMainBackground));
        canvas.drawCircle(timeLineScaleCX,timeLineScaleCY,timeLineScaleRadius,mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(timeLineStrokeWidth);
        mPaint.setColor(mContext.getColor(R.color.gsMainDraw));
        canvas.drawCircle(timeLineScaleCX,timeLineScaleCY,timeLineScaleRadius,mPaint);

        //画时间文字
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(clockTimeStrSize);
        mPaint.setAntiAlias(true);
        float textX = timeLineScaleCX - (getStringWidth(planTimeStr) / 2);
        float textY = timeLineY - timeLineScaleRadius - timeLineStrokeWidth
                - getStringBottomSpace();
        canvas.drawText(planTimeStr,textX,textY,mPaint);
    }

    private void drawTimeLineScaleDashLine(Canvas canvas,float length) {
        float dashLineX = timeLineScaleCX;
        float dashLineStartY = timeLineY + timeLineScaleRadius + timeLineStrokeWidth
                + GsDimensionUtil.dp2px(4);
//        float dashLineEndY = timeLineY + clockRadius + timeLineStrokeWidth + dashLineWidth;
        float dashLineEndY = dashLineStartY + length;

        drawDashLine(canvas,dashLineX,dashLineStartY,dashLineX,dashLineEndY,dashLineWidth,mPaint);

        timeLineScaleBottom = dashLineEndY;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void drawPlanStr(Canvas canvas) {
        mPaint.reset();
        mPaint.setColor(mContext.getColor(R.color.gsMainDraw));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(clockTimeStrSize);

        float textMaxX = (getWidth() - (getStringWidth(planStr) / 2));
        float x1 = textMaxX < timeLineScaleCX ?
                textMaxX : (timeLineScaleCX -  (getStringWidth(planStr) / 2));
        float x2 = clockCY + GsDimensionUtil.dp2px(timeLineStrokeWidth);
        float textX = x1 > x2 ? x1 : x2;

        float textY = timeLineScaleBottom - getStringHeight() + GsDimensionUtil.dp2px(4);
        canvas.drawText(planStr,textX,textY,mPaint);

        planStrBottom = textY + getStringBottomSpace();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void drawState(Canvas canvas) {
        drawClockDashLine(canvas);
        drawStateStr(canvas);
        drawStateDashLine(canvas);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void drawClockDashLine(Canvas canvas) {
        //画虚线 上段
        float dashLineStartX = clockCX;
        float dashLineStartY = timeLineY + clockRadius + timeLineStrokeWidth
                + GsDimensionUtil.dp2px(4);
        float dashLineEndX = clockCX;
        float dashLineEndY = planStrBottom + GsDimensionUtil.dp2px(16);

        mPaint.setColor(mContext.getColor(R.color.gsMainDraw));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(timeLineStrokeWidth);

        drawDashLine(canvas,dashLineStartX,dashLineStartY,dashLineEndX,dashLineEndY
                ,dashLineWidth,mPaint);

        clockDashLineBottom = dashLineEndY;
    }

    private void drawStateStr(Canvas canvas) {
        //文字
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(stateStrSize);

        float textX = clockCX - getStringWidth(stateStr) / 2;
        float textY = clockDashLineBottom + GsDimensionUtil.dp2px(8) - getStringHeight();

        canvas.drawText(stateStr,textX,textY,mPaint);
        stateStrBottom = textY + getStringBottomSpace();
    }

    private void drawStateDashLine(Canvas canvas) {
        float dashLineStartX = clockCX;
        float dashLineEndX = clockCX;
        float dashLineStartY = stateStrBottom + GsDimensionUtil.dp2px(8);
        float dashLineEndY = dashLineStartY + GsDimensionUtil.dp2px(32);

        mPaint.setStyle(Paint.Style.STROKE);
        drawDashLine(canvas,dashLineStartX,dashLineStartY,dashLineEndX,dashLineEndY
                ,GsDimensionUtil.dp2px(2),mPaint);
        setMeasuredDimension(getWidth(), (int) dashLineEndY);
    }

    /**
     * 绘制虚线的工具方法
     * @param canvas
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param dashWidth 虚线间隔 的长度
     * @param mPaint
     */
    /*
    * 工具方法
    * */
    @TargetApi(Build.VERSION_CODES.M)
    private void drawDashLine(Canvas canvas, float startX, float startY, float endX, float endY, float dashWidth, Paint mPaint) {
        PathEffect effects = new DashPathEffect(new float[] {dashWidth * 4, dashWidth},GsDimensionUtil.dp2px(2));
        mPaint.setColor(mContext.getColor(R.color.gsMainDraw));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setPathEffect(effects);

        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(endX, endY);
        canvas.drawPath(path,mPaint);
    }

    private float getStringWidth(String s) {
        float width = mPaint.measureText(s);
        return width;
    }

    private float getStringHeight() {
        Paint.FontMetrics fm = mPaint.getFontMetrics();
//        clockTimeStrHeight = (float) Math.ceil(fm.descent - fm.top);
        float height = fm.ascent;
        return height;
    }

    private float getStringBottomSpace() {
        Paint.FontMetrics fm = mPaint.getFontMetrics();
//        clockTimeStrHeight = (float) Math.ceil(fm.descent - fm.top);
        float bottomSpace = fm.bottom;
        return bottomSpace;
    }

    public void updateTime() {
        this.currTime = System.currentTimeMillis();
        Date date = new Date(currTime);
        currTimeStr = dateFormat.format(date);

        updateView();
    }

    public void setPlan(long time,String planStr) {
        this.planStr = planStr;
        this.planAddTime = System.currentTimeMillis();
        setPlanTime(time);
    }

    public void setPlanTime(long time) {
        this.planTime = time;
        Date date = new Date(time);
        planTimeStr = dateFormat.format(date);

        planTimeLeftLength = rightTimeLineLength * (((float)(currTime / timeUnit - planAddTime / timeUnit))
                / ((float) (planTime / timeUnit - planAddTime / timeUnit)));

        updateTime();
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
        updateView();
    }

    public void setTimerListener(TimerListener listener) {
        this.listener = listener;

    }

    public void updateView() {
        invalidate();
    }

    public static interface TimerListener {
        void onStartClock();
        void onTimerfinish();
    }
}
