/*
 * Everything I build is free. All Rights Reserved.
 * A singing computer scientist.
 * https://joshuajangblog.wordpress.com/
 */

package com.poscoict.fcmsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by 장 기효 on 2016-12-22.
 */

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "FCMSample";
    String str = null;

    Intent intent;

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "@@ onCreate SecondActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        intent = getIntent();
        str = intent.getStringExtra("push_content");

        textView = (TextView) findViewById(R.id.contentTextView);
        textView.setText("수신한 푸쉬 메시지 내용 : " + str);
    }
}
