package com.chris.mystudy.customview;

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

public class CustomViewFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.huawei_poweroff_charging)
    Button huaweiCharingButtom;
    @BindView(R.id.radar_view)
    Button radarButton;
    @BindView(R.id.shadow_view)
    Button shadowButton;
    @BindView(R.id.gaussian)
    Button gaussianButton;
    @BindView(R.id.touch_view)
    Button touchCircleButton;
    @BindView(R.id.playGame)
    Button palyGameButton;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_custom_layout;
    }


    @Override
    protected void initView() {
        huaweiCharingButtom.setOnClickListener(this);
        radarButton.setOnClickListener(this);
        shadowButton.setOnClickListener(this);
        gaussianButton.setOnClickListener(this);
        touchCircleButton.setOnClickListener(this);
        palyGameButton.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.huawei_poweroff_charging:
                CustomActivity.startAction(getActivity(),R.layout.activity_custom_huawei_layout);
                break;
            case R.id.radar_view:
                CustomActivity.startAction(getActivity(),R.layout.activity_custom_radar_layout);
                break;
            case R.id.shadow_view:
                CustomActivity.startAction(getActivity(),R.layout.activity_custom_shadow_layout);
                break;
            case R.id.gaussian:
                CustomActivity.startAction(getActivity(),R.layout.activity_custom_gaussian_layout,R.id.view);
                break;
            case R.id.touch_view:
                CustomActivity.startAction(getActivity(),R.layout.activity_custom_touchcircleview_layout);
                break;
            case R.id.playGame:
                CustomActivity.startAction(getActivity(),R.layout.activity_custom_game_layout);
                break;
            default:
                break;
        }
    }
}
