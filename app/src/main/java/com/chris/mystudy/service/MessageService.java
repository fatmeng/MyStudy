package com.chris.mystudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created on 17/4/10.
 * Author : chris
 * Email  : mengqi@analysys.com.cn
 * Detail :
 */

public class MessageService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"service is onCreate",Toast.LENGTH_SHORT).show();
    }
}
