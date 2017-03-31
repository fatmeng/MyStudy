package com.chris.mystudy.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chris.mystudy.R;
import com.chris.mystudy.base.BaseActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @Override
    public Toolbar setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.act_left_enter,R.anim.act_right_exit);
            }
        });
        mToolbar.setTitle("登录");
        return mToolbar;
    }

    @Override
    public int setContentId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    public static void actionStart(Context mContext) {
        Intent intent = new Intent(mContext,LoginActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.act_left_enter,R.anim.act_right_exit);
    }


}
