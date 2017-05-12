package com.chris.mystudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.chris.mystudy.service.JobAwakenService;

/**
 * Created by chris on 16/8/21.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        Intent service = new Intent(SplashActivity.this, JobAwakenService.class);
        startService(service);
        finish();
    }
}
