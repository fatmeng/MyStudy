package com.chris.mystudy.adapater;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chris.mystudy.R;
import com.chris.mystudy.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 16/8/29.
 */
public class RecyclerAdapterForNews extends RecyclerView.Adapter<ViewHolder>{





    private static final int TYPE_VIEWPAGER = 0;
    private static final int TYPE_NEWS = 1;


    //自定义实现
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    private List<News> viewPagerDatas;
    private List<News> datas;

    public RecyclerAdapterForNews(){
        viewPagerDatas = new ArrayList<News>();
        this.datas = new ArrayList<News>();
    }

    public void setDatas(List<News> news){
        datas.clear();
        appendDatas(news);
    }

    public void setViewPagerDatas(List<News> viewPagerDatas, List<News> datas){
        this.viewPagerDatas.clear();
        this.viewPagerDatas = viewPagerDatas;
        this.datas.clear();
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void appendDatas(List<News> news){
        datas.addAll(news);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("qi.meng","onCreateViewHolder  viewType: " + viewType + ";  getItemViewType : " + getItemViewType(viewType));
        View itemView = null;
        switch (viewType){
            case TYPE_VIEWPAGER:
//                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_viewpager,parent,false);
//                return new ViewPagerHolder(itemView);
            case TYPE_NEWS:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerlist_news_item,parent,false);
                return new NewsHolder(itemView);

        }
        throw new IllegalArgumentException("RecyclerAdapterForNews item'type is wrong!!!");
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("qi.meng","onBindViewHolder  position: " + position + ";  getItemViewType : " + getItemViewType(position));
        switch (getItemViewType(position)){
            case TYPE_VIEWPAGER:
//                onBindViewHolderForViewPager((ViewPagerHolder)holder);
                break;
            case TYPE_NEWS:
                onBindViewHolderForNews((NewsHolder)holder,position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        if(position == 0){
//            return TYPE_VIEWPAGER;
//        }else {
//            return TYPE_NEWS;
//        }
        return TYPE_NEWS;
    }

//    private void onBindViewHolderForViewPager(ViewPagerHolder holder) {
//        if (holder.viewPager.getAdapter() == null){
//            mPagerAdapter = new LoopViewPagerAdapter(holder.viewPager,holder.indicator);
//            holder.viewPager.setAdapter(mPagerAdapter);
//            holder.viewPager.setOnPageChangeListener(mPagerAdapter);
//        }
//        mPagerAdapter.setList(viewPagerDatas);
//    }

    private void onBindViewHolderForNews(final NewsHolder holder, final int position) {
        holder.news_title.setText(datas.get(position).title);
        holder.news_timestamp.setText("fuck_5分钟前");
        holder.news_used_times.setText("fuck_500次");
        holder.news_avatar.setImageResource(R.drawable.leak_canary_icon);
        holder.news_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener != null){
                    mOnItemClickLitener.onItemClick(holder.news_layout,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        int viewPagerCount = viewPagerDatas.isEmpty()?0:1;
        return datas.size() + viewPagerCount;
    }

    public static class NewsHolder extends ViewHolder {
        LinearLayout news_layout;
        ImageView news_avatar;
        TextView news_title;
        TextView news_used_times;
        TextView news_timestamp;

        NewsHolder(View itemView){
            super(itemView);
            news_layout = (LinearLayout)itemView.findViewById(R.id.news_layout);
            news_avatar = (ImageView)itemView.findViewById(R.id.news_avatart);
            news_title = (TextView)itemView.findViewById(R.id.news_title);
            news_used_times = (TextView)itemView.findViewById(R.id.news_used_times);
            news_timestamp = (TextView)itemView.findViewById(R.id.news_timestamps);
        }
    }


//    private static class ViewPagerHolder extends ViewHolder {
//        ViewPager viewPager;
//        ViewGroup indicator;
//
//        public ViewPagerHolder(View itemView) {
//            super(itemView);
//            viewPager = (ViewPager)itemView.findViewById(R.id.viewPager);
//            indicator = (ViewGroup)itemView.findViewById(R.id.indicators);
//        }
//    }
}