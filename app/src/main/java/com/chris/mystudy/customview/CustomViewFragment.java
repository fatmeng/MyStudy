package com.chris.mystudy.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chris.mystudy.R;
import com.chris.mystudy.base.BaseFragment;

import butterknife.BindView;

/**
 * Created on 17/3/31.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class CustomViewFragment extends BaseFragment {

    @BindView(R.id.huawei_poweroff_charging)
    Button huaweiCharing;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_custom_layout;
    }


    @Override
    protected void initView() {
        huaweiCharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CustomActivity.class);
                mContext.startActivity(intent);
                getActivity().overridePendingTransition(R.anim.act_right_enter,R.anim.act_left_exit);
            }
        });
    }

    @Override
    protected void destoryView() {

    }

    public static CustomViewFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(FRAGMENT_TITLE,title);
        CustomViewFragment fragment = new CustomViewFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
