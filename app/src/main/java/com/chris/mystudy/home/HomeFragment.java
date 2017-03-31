package com.chris.mystudy.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chris.mystudy.DetailActivity;
import com.chris.mystudy.R;
import com.chris.mystudy.adapater.RecyclerAdapterForNews;
import com.chris.mystudy.base.BaseFragment;
import com.chris.mystudy.bean.News;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created on 17/3/10.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class HomeFragment extends BaseFragment implements RecyclerAdapterForNews.OnItemClickLitener{


    @BindView(R.id.coordinator_layout)CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.recycler_view)RecyclerView mRecyclerView;

    public static HomeFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(FRAGMENT_TITLE,title);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.home_layout;
    }

    @Override
    protected void initView() {
        RecyclerAdapterForNews newsAdapter = new RecyclerAdapterForNews();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        List<News> datas = new ArrayList<News>();
        News news = new News();
        for (int x = 0; x < 20; x++) {
            news.setTitle("fuck_behind");
            datas.add(news);
        }
        newsAdapter.setDatas(datas);
        newsAdapter.setOnItemClickLitener(this);
        mRecyclerView.setAdapter(newsAdapter);
    }

    @Override
    protected void destoryView() {

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.enter_animation,R.anim.exit_animation);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
