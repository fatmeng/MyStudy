package com.chris.mystudy.bean;

/**
 * Created by chris on 16/8/29.
 */
public class News {
    public String web_url;
    public String title;
    public String img_url;
    public int used_times;
    public long timestamp;



    public News(){};
    public News(String web_url, String title, String img_url, int used_times, long timestamp){
        this.web_url =web_url;
        this.title = title;
        this.img_url = img_url;
        this.used_times = used_times;
        this.timestamp = timestamp;
    }

    public News setWeb_url(String web_url) {
        this.web_url = web_url;
        return this;
    }

    public News setTitle(String title) {
        this.title = title;
        return this;
    }

    public News setImg_url(String img_url) {
        this.img_url = img_url;
        return this;
    }

    public News setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public News setUsed_times(int used_times) {
        this.used_times = used_times;
        return this;
    }
}
