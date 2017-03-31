package com.chris.mystudy.customview;

import android.support.v7.widget.Toolbar;

import com.chris.mystudy.R;
import com.chris.mystudy.base.BaseActivity;

/**
 * Created on 17/3/31.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */
public class CustomActivity extends BaseActivity{
    @Override
    public Toolbar setToolbar() {
        return null;
    }

    @Override
    public int setContentId() {
        return R.layout.activity_custom_layout;
    }

    @Override
    public void initView() {

    }
}
