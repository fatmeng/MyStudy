package com.chris.mystudy.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity {

    //需要展示的Activity是否需要显示NavigationStatus
    private boolean isShowNavagationBar = false;
    public boolean showNavagationBar(){
        return isShowNavagationBar;
    }

    //需要子类实现的类,传进来Toolbar
    public abstract Toolbar setToolbar();

    //子类需要传进来的layoutID
    public abstract int setContentId();

    //子类需要实现
    public abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestFeature() must be called before adding content
        setContentView(setContentId());
        ButterKnife.bind(this);
//        setStatusBar(!showNavagationBar());
        setToolbar();
        initView();

    }

}
