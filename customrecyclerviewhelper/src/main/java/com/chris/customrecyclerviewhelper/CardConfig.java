package com.chris.customrecyclerviewhelper;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created on 17/3/17.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail : 参考https://juejin.im/post/5856825c1b69e6006c96a58b
 */

public class CardConfig {
    //需要显示的卡片数量
    public static int MAX_SHOW_COUNT =3;
    //每一级Scale相差的值
    public static float SCALE_GAP = 0.05f;
    public static int TRANS_Y_GAP;
    public static final int TRANS_Y_GAP_DEFALUT_SRC = 15;
    public static void initConfig(Context mContext){
        TRANS_Y_GAP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,TRANS_Y_GAP_DEFALUT_SRC,mContext.getResources().getDisplayMetrics());
    }

    public static void initConfig(Context mContext,int maxShowCount,float scaleGap,int transYGap){
        //需要对参数限制最大值与最小值
        MAX_SHOW_COUNT = maxShowCount;
        SCALE_GAP = scaleGap;
        TRANS_Y_GAP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,transYGap,mContext.getResources().getDisplayMetrics());
    }



}
