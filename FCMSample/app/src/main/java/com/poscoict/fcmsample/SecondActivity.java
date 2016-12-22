/*
 * Everything I build is free. All Rights Reserved.
 * A singing computer scientist.
 * https://joshuajangblog.wordpress.com/
 */

package com.poscoict.fcmsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by 장 기효 on 2016-12-22.
 */

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "FCMSample";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        Log.i(TAG, "@@ SecondActivity onCreate ");
    }
}
