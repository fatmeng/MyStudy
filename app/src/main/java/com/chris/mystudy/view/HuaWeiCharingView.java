package com.chris.mystudy.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created on 17/3/31.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class HuaWeiCharingView extends View {

    //默认宽高(在布局中设置wrap_content时,使用)
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 450;
    private static final String TAG = "HuaWeiCharingView";



    //视图的中心点
    private static int pivotX = 0;
    private static int pivotY = 0;

    //视图的宽高
    private static int widht = 0;
    private static int height = 0;

    //以中心点(pivotX,pvotY)为圆的半径
    private static int radius = 0;

    //属性动画
    ValueAnimator animator;

    //
    Runnable runnable;

    Paint mPaint;


    //rotate角度,起始值为0
    public static int rotatedValue = 0;
    //rotate递增角度
    public static final int ROTATED_GAP = 5;
    //设置Rotated的时间Interval
    public static final long ROTATED_INTERVAL = 160L;
    //是否Rotate
    private boolean isRotate = false;
    public HuaWeiCharingView(Context context) {
        super(context);
        init();
    }

    public HuaWeiCharingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HuaWeiCharingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HuaWeiCharingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(30);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        animator = new ValueAnimator();
    }

    private void rotate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    rotatedValue += ROTATED_GAP;
                    rotatedValue %= 360;
                    postInvalidate();
                    if (!isRotate){
                        break;
                    }
                    try {
                        Thread.sleep(160);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void stopRotate(){
        isRotate = false;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int meausreWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (meausreWidthMode == MeasureSpec.AT_MOST){
            measureWidthSize = Math.min(DEFAULT_WIDTH,measureWidthSize);
        }
        if (measureHeightMode == MeasureSpec.AT_MOST){
            measureHeightSize = Math.min(DEFAULT_HEIGHT,measureHeightSize);
        }
        setMeasuredDimension(measureWidthSize, measureHeightSize);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        widht = getWidth();
        height = getHeight();
        radius = (int) (Math.min(widht,height)/2.5);
        pivotX = getWidth()/2;
        pivotY = getHeight()/2;

    }






    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(rotatedValue,pivotX,pivotY);
        //画三角形
        Path path = new Path();
        int pivotAY = (int) (pivotY*0.8);
        path.moveTo(pivotX,pivotAY - radius);
        path.lineTo(pivotX - radius,pivotAY + radius);
        path.quadTo((float) (pivotX*(1-Math.random())),pivotY,pivotX - radius,pivotAY + radius);
        path.quadTo(pivotX, (float) ((pivotY+radius)*(1+Math.random())),pivotX + radius ,pivotAY + radius);
        path.quadTo((float) ((pivotX+radius)*(1-Math.random())),pivotY,pivotX,pivotAY - radius);
        canvas.drawPath(path,mPaint);

        path.reset();
        int pivoatBY = (int)(pivotY * 1.2);
        path.moveTo(pivotX - radius,pivoatBY - radius);
        path.lineTo(pivotX + radius,pivoatBY - radius);
        path.lineTo(pivotX,pivoatBY + radius);
        path.close();
        canvas.drawPath(path,mPaint);

        canvas.save();
        Log.e(TAG, "onDraw: count:" + count++);
    }

    int count = 0;
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isRotate = true;
        rotate();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopRotate();
    }
}
