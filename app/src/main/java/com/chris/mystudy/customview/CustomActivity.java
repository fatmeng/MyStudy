package com.chris.mystudy.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chris.mystudy.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created on 17/3/31.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */
public class CustomActivity extends SwipeBackActivity{
    private static final String RESOURCE_LAYOUT_ID = "resource_layout_id";
    private static final int ERROR_ID = 0;
    private static final String RESOUCE_IMGAGE_ID = "resouce_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int resouceId = getIntent().getIntExtra(RESOURCE_LAYOUT_ID,0);
        int resourceImageId = getIntent().getIntExtra(RESOUCE_IMGAGE_ID,0);
        if(resouceId != ERROR_ID){
            setContentView(resouceId);
        }
        if (resourceImageId != ERROR_ID){
            ImageView src = (ImageView)findViewById(resourceImageId);
            String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491738762642&di=90ee6094832649cdc83b0e6f07d26cff&imgtype=0&src=http%3A%2F%2Fimg01.taopic.com%2F141114%2F318762-1411140J63541.jpg";
            Glide.with(this).load(url).bitmapTransform(new BlurTransformation(this,20,4)).into(src);
        }
    }

    public static void startAction(Context mContext,int resourceId){
        Intent intent = new Intent(mContext,CustomActivity.class);
        intent.putExtra(RESOURCE_LAYOUT_ID,resourceId);
        mContext.startActivity(intent);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        overridePendingTransition(R.anim.act_right_enter, R.anim.act_left_exit);
    }

    /**
     * 测试用,一点标准没有
     * @param mContext
     * @param resourceId
     * @param srcId
     */
    public static void startAction(Context mContext,int resourceId,int srcId){
        Intent intent = new Intent(mContext,CustomActivity.class);
        intent.putExtra(RESOURCE_LAYOUT_ID,resourceId);
        intent.putExtra(RESOUCE_IMGAGE_ID,srcId);
        mContext.startActivity(intent);
    }
}
