package com.chris.customrecyclerviewhelper.manager;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created on 17/3/17.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail : 模仿探探 ,参考http://yuqirong.me/2017/03/05/玩转仿探探卡片式滑动效果/
 */

public class CardLayoutManager extends RecyclerView.LayoutManager{
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {

        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        //先移出所有的view
        removeAllViews();//RecyclerView类库中有一个ChildHelper类,将其中持有的child移除
        //在布局之前,将所有的子View先Detach掉,放入到Scrap缓存中
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();


    }
}
