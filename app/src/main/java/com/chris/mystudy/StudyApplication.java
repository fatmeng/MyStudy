package com.chris.mystudy;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Chris on 2017/3/8.
 */

public class StudyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!LeakCanary.isInAnalyzerProcess(this)){
            LeakCanary.install(this);
        }

    }
}