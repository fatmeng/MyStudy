package com.chris.mystudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.chris.mystudy.R;

/**
 * Created on 17/4/10.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class TouchCirlCleView extends View {

    private final static float CIRCLE_RADIUS_DEFAULT = 15;
    private static final int WIDTH_DEFAULT = 500;
    private static final int HEIGHT_DEFAULT = 800;
    private float circleRadius;

    private Bitmap src;
    private Bitmap dst;

    private int width,height;
    private float touchX,touchY;
    private float touchOldX,touchOldY;

    private Paint mPaint;

    private PorterDuffXfermode xfermode;
    private RectF rectf;


    //一个状态集合
    private int curentState = IDLE;
    private static final int IDLE = 0x001;//表示没有显示
    private static final int MOVE = 0x010;//表示正在移动
    private static final int STOP = 0x100;//表示显示,但没有移动
    public TouchCirlCleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs,defStyleAttr);
    }

    public TouchCirlCleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    public TouchCirlCleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public TouchCirlCleView(Context context) {
        super(context);
        init(null,0);
    }

    private void init(AttributeSet attrs,int defStyleAttr){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TouchCilrView,defStyleAttr,0);
        circleRadius = a.getDimension(R.styleable.TouchCilrView_circleRadius,CIRCLE_RADIUS_DEFAULT);
        Drawable drawable = a.getDrawable(R.styleable.TouchCilrView_bgSrc);
        if (drawable == null){
            src = BitmapFactory.decodeResource(getResources(),R.drawable.touch_circle_view_default_bg);
        }else {
            src = ((BitmapDrawable)drawable).getBitmap();
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (measureWidthMode == MeasureSpec.AT_MOST){
            measureWidthSize = Math.min(measureWidthSize,WIDTH_DEFAULT);
        }
        if (measureHeightMode == MeasureSpec.AT_MOST){
            measureHeightSize = Math.min(measureHeightSize,HEIGHT_DEFAULT);
        }
        setMeasuredDimension(measureWidthSize,measureHeightSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed){
            width = getWidth() - getPaddingLeft() - getPaddingRight();
            height = getHeight() - getPaddingTop() - getPaddingBottom();
            rectf = new RectF(getPaddingLeft(),getPaddingTop(),width,height);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(src,0,0,null);//设置背景
        int layerId = canvas.saveLayer(rectf,null,Canvas.ALL_SAVE_FLAG);
        canvas.drawRect(rectf,mPaint); //设置遮罩层
        switch (curentState){
            case IDLE: //不处理,不绘制
                break;
            case MOVE: //绘制
            case STOP: //绘制
                drawCircle(canvas,layerId);
                break;
        }

    }

    private void drawCircle(Canvas canvas,int layerId) {

        mPaint.setXfermode(xfermode);
        canvas.drawCircle(touchX,touchY,circleRadius,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                curentState = STOP;
                break;
            case MotionEvent.ACTION_MOVE:
                touchX = event.getX();
                touchY = event.getY();
                curentState = MOVE;
                break;
            case MotionEvent.ACTION_UP:
                curentState = IDLE;
                break;
        }
        invalidate();
        return true;
    }

    /**
     * @param options
     * @param reqeuestWidth
     * @param requestHeight
     * @return  返回对应options的采样率 inSampleSize
     */
    private int caculateInSampleSize(BitmapFactory.Options options,int reqeuestWidth,int requestHeight){
        int realOptionsWidth = options.outWidth;
        int realOptionsHeight = options.outHeight;
        int isSameleSize = 1;
        if (realOptionsWidth > reqeuestWidth || realOptionsHeight > requestHeight){
            realOptionsWidth >>= 1 ;
            realOptionsHeight >>= 1;
        }
        while (realOptionsWidth/isSameleSize > reqeuestWidth && realOptionsHeight/isSameleSize > requestHeight){
            isSameleSize <<=1;
        }
        return isSameleSize;
    }
}


