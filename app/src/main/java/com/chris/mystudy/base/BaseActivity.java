package com.chris.mystudy.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentId());
        ButterKnife.bind(this);
        setStatusBar(!showNavagationBar());
        if (setToolbar() == null){
            //TODO:处理不显示ActionBar的情况
        }else {
            setSupportActionBar(setToolbar());
        }

    }

    /**
     * 设置透明状态栏与导航栏
     * @param navi true不设置导航栏|false设置导航栏
     */
    private void setStatusBar(boolean navi) {
        //api>21,全透明状态栏和导航栏;api>19,半透明状态栏和导航栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            if (navi) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//状态栏不会被隐藏但activity布局会扩展到状态栏所在位置
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//导航栏不会被隐藏但activity布局会扩展到导航栏所在位置
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (navi) {
                //半透明导航栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
            //半透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
