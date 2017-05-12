package com.chris.mystudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.chris.mystudy.R;

/**
 * Created on 17/4/8.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail : 学习人家的
 */

public class RadarView extends View {

    private static final int WIDTH_DEFALUT = 450;
    private static final int HEIGHT_DEFALUT = 450;

    private static final int CIRCLE_NUM_DEFALUT = 4;
    private static final int START_COLOR_DEFAULT = Color.TRANSPARENT;
    private static final int END_COLOR_DEFAULT = Color.WHITE;
    private static final int BG_COLOR_DEFALUT = Color.GRAY;
    private int circleNum = CIRCLE_NUM_DEFALUT;

    private int startColor = 0;
    private int endColor = 0;
    private int bgColor = 0;

    private Shader radarShader;

    private float pivotX,pivotY;
    private int width,height;
    //半径
    private int radius;
    private Paint bgPaint;
    private Paint radiusPaint;
    private Paint radarScanPaint;
    private Matrix mMatrix;

    public RadarView(Context context) {
        super(context);
        init(null,0);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs,defStyleAttr);
    }

    private void init(AttributeSet attrs,int defSytleAttr){
        if (attrs != null){
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RadarView,defSytleAttr,0);
            circleNum = a.getInteger(R.styleable.RadarView_circle_num, CIRCLE_NUM_DEFALUT);
            startColor = a.getColor(R.styleable.RadarView_startColor,START_COLOR_DEFAULT);
            endColor = a.getColor(R.styleable.RadarView_endColor,END_COLOR_DEFAULT);
            bgColor = a.getColor(R.styleable.RadarView_bgColor,BG_COLOR_DEFALUT);

            bgPaint = new Paint();
            bgPaint.setColor(bgColor);
            bgPaint.setAntiAlias(true);
            radiusPaint = new Paint();
            radiusPaint.setStyle(Paint.Style.STROKE);
            radiusPaint.setColor(Color.WHITE);
            radiusPaint.setAntiAlias(true);
            radarShader = new SweepGradient(0,0,startColor,endColor);
            radarScanPaint = new Paint();
            radarScanPaint.setAntiAlias(true);
            radarScanPaint.setShader(radarShader);
            //
            mMatrix = new Matrix();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST){
            widthSize = Math.min(widthSize,WIDTH_DEFALUT);
        }
        if (heightMode == MeasureSpec.AT_MOST){
            heightSize = Math.min(heightSize,HEIGHT_DEFALUT);
        }
        int realSize = Math.min(widthSize,heightSize);
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed){
            width = getWidth() - getPaddingLeft() - getPaddingRight();
            height = getHeight() - getPaddingTop() - getPaddingBottom();
            pivotX = getWidth()/2;
            pivotY = getHeight()/2;
            radius = Math.min(width,height)/2;

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(pivotX,pivotY);
        canvas.drawCircle(0,0,radius,bgPaint);
        for (int i = 1;i <= circleNum;i++){
            canvas.drawCircle(0,0,radius*(1-i/(float)circleNum),radiusPaint);
        }
        canvas.drawLine(-radius,0,radius,0,radiusPaint);
        canvas.drawLine(0,-radius,0,radius,radiusPaint);


        canvas.concat(mMatrix);
//        canvas.save();
//        canvas.rotate(scan_angle);
        canvas.drawCircle(0,0,radius,radarScanPaint);
//        canvas.restore();
    }


    private float scan_angle = 0;
    private static final float SCAN_ANGLE_INTERVAL = 5;
    private ScanRunnable scanR = new ScanRunnable();
    private static final long SCAN_TIME_INTERVAL = 150L;
    private void startScan(){
        isStopScan = false;
        postDelayed(scanR,SCAN_TIME_INTERVAL);

    }

    private void stopScan(){
        isStopScan = true;
    }

    private boolean isStopScan = false;
    private class ScanRunnable implements Runnable{

        @Override
        public void run() {
            scan_angle += SCAN_ANGLE_INTERVAL;
            mMatrix.reset();
            mMatrix.preRotate(scan_angle,0,0);
            invalidate();
            if (!isStopScan){
                startScan();
            }
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startScan();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScan();
    }
}
