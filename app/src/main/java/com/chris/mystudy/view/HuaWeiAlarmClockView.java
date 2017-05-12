package com.chris.mystudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.chris.mystudy.R;

/**
 * Created on 17/3/31.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class HuaWeiAlarmClockView extends View {

    @ColorInt
    private static final int BLUE_TRANSLUCENCE        = 0x880000FF;
    @ColorInt
    private static final int YELLOW_TRANSLUCENCE      = 0x88FFFF00;

    @ColorInt
    private static int startColor = BLUE_TRANSLUCENCE;
    @ColorInt
    private static int endColor = YELLOW_TRANSLUCENCE;
    @ColorInt
    private static int centerColor = Color.GRAY;
    //此值为sp值.需要在构造里转换成px
    private static final float TEXT_SIZE_DEFAULT = 15;
    private static float textSize = TEXT_SIZE_DEFAULT;
    private static String text = "";




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
    private static float radius = 0;


    //贝塞尔曲线Paint
    Paint mPaintCirCle;
    //中心背景圆Paint
    Paint mPaintDefault;
    //文字Paint
    Paint mPaintText;

    //rotate角度,起始值为0
    public static int rotatedValue = 0;
    //rotate递增角度
    public static final int ROTATED_GAP = 2;
    //设置Rotated的时间Interval
    public static final long ROTATED_INTERVAL = 160L;
    //是否Rotate
    private boolean isRotate = false;

    //设置呼吸效果的shader
    Shader mShader;
    //处理背景图的呼吸效果频率
    private float ratio = 0.50f;

    public HuaWeiAlarmClockView(Context context) {
        super(context,null);
    }

    public HuaWeiAlarmClockView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HuaWeiAlarmClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public HuaWeiAlarmClockView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HuaWeiAlarmClockView,defStyleAttr,defStyleRes);
        if(a != null){
            int count = a.getIndexCount();
            for (int i = 0;i<count;i++){
                int attr = a.getIndex(i);
                switch (attr){
                    case R.styleable.HuaWeiAlarmClockView_shaderStartColor:
                        startColor = a.getColor(R.styleable.HuaWeiAlarmClockView_shaderStartColor,BLUE_TRANSLUCENCE);
                        startColor &= 0x88FFFFFF;//半透明化
                        break;
                    case R.styleable.HuaWeiAlarmClockView_shaderEndColor:
                        endColor = a.getColor(R.styleable.HuaWeiAlarmClockView_shaderEndColor,YELLOW_TRANSLUCENCE);
                        endColor &= 0x88FFFFFF;//半透明化
                        break;
                    case R.styleable.HuaWeiAlarmClockView_centerColor:
                        centerColor = a.getColor(R.styleable.HuaWeiAlarmClockView_centerColor,Color.GRAY);
                        break;
                    case R.styleable.HuaWeiAlarmClockView_textSize:
                        textSize = a.getDimensionPixelSize(R.styleable.HuaWeiAlarmClockView_textSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,TEXT_SIZE_DEFAULT,context.getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.HuaWeiAlarmClockView_text:
                        text = a.getString(R.styleable.HuaWeiAlarmClockView_text);
                    default:
                        break;
                }
            }

        }
        a.recycle();

        mPaintCirCle = new Paint();
        mPaintCirCle.setStrokeWidth(60);
        mPaintCirCle.setAntiAlias(true);
        mShader = new LinearGradient(0,0,100,100,startColor,endColor,Shader.TileMode.MIRROR);
        mPaintCirCle.setShader(mShader);

        mPaintDefault = new Paint();
        mPaintDefault.setColor(centerColor);
        mPaintDefault.setAntiAlias(true);

        mPaintText = new Paint();
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(textSize);
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
        radius = (int) (Math.min(widht,height)/4);//测试出来的除4.因贝塞尔曲线会扩充边界
        pivotX = getWidth()/2;
        pivotY = getHeight()/2;

    }






    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("HuaWeiAlarmColckView","onDraw()");
        canvas.save();
        canvas.translate(pivotX,pivotY);
        canvas.rotate(rotatedValue);

        if (true){
            //通过四条三阶贝塞尔曲线画圆
            Path path = new Path();
            path.moveTo(-radius,0);
            path.cubicTo(-radius, (float) (-radius*((1+Math.random())/4 + 0.25)),(float) (-radius*((1+Math.random())/4 + 0.25)),-radius,0,-radius);
            path.cubicTo((float) (radius*((1+Math.random())/4 + 0.25)),-radius,radius,(float) (-radius*((1+Math.random())/4 + 0.25)),radius,0);
            path.cubicTo(radius,(float) (radius*((1+Math.random())/4 + 0.25)),(float) (radius*((1+Math.random())/4 + 0.25)),radius,0,radius);
            path.cubicTo((float) (-radius*((1+Math.random())/4 + 0.25)),radius,-radius,(float) (radius*((1+Math.random())/4 + 0.25)),-radius,0);
            canvas.drawPath(path, mPaintCirCle);
            canvas.rotate(45);
            path.reset();
            path.moveTo(-radius,0);
            path.cubicTo(-radius, (float) (-radius*((1+Math.random())/4 + 0.25)),(float) (-radius*((1+Math.random())/4 + 0.25)),-radius,0,-radius);
            path.cubicTo((float) (radius*((1+Math.random())/4 + 0.25)),-radius,radius,(float) (-radius*((1+Math.random())/4 + 0.25)),radius,0);
            path.cubicTo(radius,(float) (radius*((1+Math.random())/4 + 0.25)),(float) (radius*((1+Math.random())/4 + 0.25)),radius,0,radius);
            path.cubicTo((float) (-radius*((1+Math.random())/4 + 0.25)),radius,-radius,(float) (radius*((1+Math.random())/4 + 0.25)),-radius,0);
            canvas.drawPath(path, mPaintCirCle);
        }else {
            //获取呼吸效果的参数,及贝塞尔曲线的控制点位置相对radius的值.现在取值为radius的0.25至0.7倍之间
            ratio = scaleRatio();
            //通过四条三阶贝塞尔曲线画圆
            Path path = new Path();
            path.moveTo(-radius,0);
            path.cubicTo(-radius, -radius*ratio,-radius*ratio,-radius,0,-radius);
            path.cubicTo(radius*ratio,-radius,radius,-radius*ratio,radius,0);
            path.cubicTo(radius,radius*ratio,radius*ratio,radius,0,radius);
            path.cubicTo(-radius*ratio,radius,-radius,radius*ratio,-radius,0);
            canvas.drawPath(path, mPaintCirCle);
            canvas.rotate(45);
            path.reset();
            path.moveTo(-radius,0);
            path.cubicTo(-radius, -radius*ratio,-radius*ratio,-radius,0,-radius);
            path.cubicTo(radius*ratio,-radius,radius,-radius*ratio,radius,0);
            path.cubicTo(radius,radius*ratio,radius*ratio,radius,0,radius);
            path.cubicTo(-radius*ratio,radius,-radius,radius*ratio,-radius,0);
            canvas.drawPath(path, mPaintCirCle);
        }

        //画圆
        canvas.drawCircle(0,0, (float) (radius*0.95), mPaintDefault);

        canvas.restore();


        canvas.translate(pivotX,pivotY);
        //画字
        Rect rect = new Rect();
        mPaintText.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text,0-rect.width()/2,0+rect.height()/2,mPaintText);
    }

    private float scaleRatio() {
        if (ratio < 0.75){
            ratio += 0.05;
        }else if(ratio > 0.25){
            ratio -= 0.05;
        }
        return ratio;
    }

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
