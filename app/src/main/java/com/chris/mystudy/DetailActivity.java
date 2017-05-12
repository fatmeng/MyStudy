package com.chris.mystudy;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chris.mystudy.base.BaseActivity;

import butterknife.BindView;

/**
 * Created on 17/3/14.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class DetailActivity extends BaseActivity {
    @BindView(R.id.detail_text)
    TextView mTextView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.detail_collapsingtoolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Override
    public Toolbar setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setTitle("娃娃");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.enter_animation,R.anim.exit_animation);
            }
        });
        return mToolbar;
    }

    @Override
    public int setContentId() {
        return R.layout.activity_detail;

    }

    @Override
    public void initView() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i<50;i++){
            sb.append("my baby \n");
        }
        mTextView.setText(new String(sb));
        mCollapsingToolbarLayout.setTitle("娃娃");
    }

    @Override
    public boolean showNavigationBar() {
        return false;
    }
}
