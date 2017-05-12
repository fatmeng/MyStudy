package com.chris.mystudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.chris.mystudy.R;

/**
 * Created on 17/4/9.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail : 倒影图片
 */

public class ShadowView extends View {

    private Paint xfermodePaint;
    private Paint mShadowPaint;
    private Drawable srcDrawable;
    private Bitmap srcBitmp;
    private Bitmap mShadowBitmap;
    private static final int WIDTH_DEFALUT = 450;
    private static final int HEIGHT_DEFALUT = 500;
    private int width,height;
    private Matrix mShadowMatrix;
    private RectF mBitmapRectF;
    private RectF mShadowBitmapRectF;
    private Shader mShadowShader;
    private Xfermode shadowXfermode;


    public ShadowView(Context context) {
        super(context);
        init(null,0);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs,defStyleAttr);
    }

    private void init(AttributeSet attrs,int defSytleAttr){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ShadowView,defSytleAttr,0);
        srcDrawable = a.getDrawable(R.styleable.ShadowView_src);
        srcBitmp = ((BitmapDrawable)srcDrawable).getBitmap();
        mShadowMatrix = new Matrix();
        mShadowMatrix.setScale(1,-1);
        mShadowBitmap = Bitmap.createBitmap(srcBitmp,0,0,srcBitmp.getWidth(),srcBitmp.getHeight(),mShadowMatrix,true);

        shadowXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        mShadowPaint = new Paint();
        mShadowPaint.setAntiAlias(true);
        mShadowPaint.setShader(mShadowShader);

        xfermodePaint = new Paint();
        xfermodePaint.setAntiAlias(true);
        xfermodePaint.setXfermode(shadowXfermode);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMeasureMode == MeasureSpec.AT_MOST){
            widthMeasureSize = Math.min(WIDTH_DEFALUT,widthMeasureSize);
        }
        if (heightMeasureMode == MeasureSpec.AT_MOST){
            heightMeasureSize = Math.min(HEIGHT_DEFALUT,heightMeasureSize);
        }
        setMeasuredDimension(widthMeasureSize,heightMeasureSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed){
            height = getHeight();
            width = getWidth();

            mBitmapRectF = new RectF(left,top,right,bottom*2/3);
            mShadowBitmapRectF = new RectF(left,bottom*2/3,right,bottom*4/3);
            //一定要注意shader对象的位置position,一定要准确,
//            mShadowShader = new LinearGradient(0, bottom*2/3, 0, bottom, new int[]{0x00000000,0x88000000,0xBB000000,0xFF000000},new float[]{0,0.3f,0.7f,1f}, Shader.TileMode.REPEAT);
            mShadowShader = new LinearGradient(0,bottom*2/3,0,bottom*4/3,new int[]{
                    0x00000000,0x88000000,0xBB000000,0xFF000000
            },null, Shader.TileMode.REPEAT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null != srcBitmp){
            canvas.drawBitmap(srcBitmp,null,mBitmapRectF,null);//draw 正图
            int layoutId = canvas.saveLayer(mShadowBitmapRectF,null,Canvas.ALL_SAVE_FLAG);
            mShadowPaint.setShader(mShadowShader);
            canvas.drawRect(mShadowBitmapRectF,mShadowPaint);//draw DST
            canvas.drawBitmap(mShadowBitmap,null,mShadowBitmapRectF,xfermodePaint);//draw SRC
            canvas.restoreToCount(layoutId);
        }
    }

    public void setImageSrc(int resId){
        srcBitmp = BitmapFactory.decodeResource(getContext().getResources(),resId);
        if (null == srcBitmp){
            try {
                throw new ShadowViewIllegalException("setImageSrc() params is Illegal!!!");
            } catch (ShadowViewIllegalException e) {
                e.printStackTrace();
            }
        }else {
            updateShadowView();
        }
    }

    private void updateShadowView() {
        mShadowBitmap = Bitmap.createBitmap(srcBitmp,0,0,srcBitmp.getWidth(),srcBitmp.getHeight(),mShadowMatrix,true);
        invalidate();
    }


    public class ShadowViewIllegalException extends Exception{
        public ShadowViewIllegalException() {
        }

        public ShadowViewIllegalException(String message) {
            super(message);
        }
    }
}
