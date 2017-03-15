package com.chris.mystudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by chris on 16/8/21.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
