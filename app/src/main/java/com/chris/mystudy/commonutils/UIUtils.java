package com.chris.mystudy.commonutils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by Chris on 2017/3/8.
 */

public class UIUtils {

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int temp = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
    /**
     * 进入沉浸模式
     * @param view view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void hideSystemUI(View view) {
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY//会自动隐藏
        );
    }

    /**
     * 退出沉浸模式
     * @param view view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void showSystemUI(View view) {
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * @param mContext
     * @param src 输入需要转换为px的sp值
     * @return 返回对应src的px值
     */
    public static float dp2px(Context mContext,int src){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,src, mContext.getResources().getDisplayMetrics());
    }




}
