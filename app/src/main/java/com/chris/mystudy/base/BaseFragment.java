package com.chris.mystudy.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Chris on 2017/3/8.
 */

public abstract class BaseFragment extends Fragment {

    public static final String FRAGMENT_TITLE = "title";
    private boolean isViewPrepared = false; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData = false; // 标识已经触发过懒加载数据
    protected Context mContext = null;


    protected abstract int getContentViewLayoutID();
    /**  懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次 */
    protected abstract void initView();

    protected abstract void destoryView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        if (getContentViewLayoutID()!=0){
            //至于不用两参方法,是为了避免参1的layout_XXX布局丢失
            return inflater.inflate(getContentViewLayoutID(),container,false);
        }else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        ButterKnife.bind(this,view);
        lazyFetchDataIfPrepared();
    }

    @Override
    public void onDestroyView() {
        destoryView();
        hasFetchData = false;
        isViewPrepared = false;
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            lazyFetchDataIfPrepared();
        }
    }

    private void lazyFetchDataIfPrepared(){
        //用户可见&&没有加载过数据&&视图已经准备好
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared){
            initView();
            hasFetchData = true;
        }else {
            hasFetchData = false;
        }
    }
}
