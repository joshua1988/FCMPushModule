/*
 * Everything I build is free. All Rights Reserved.
 * A singing computer scientist.
 * https://joshuajangblog.wordpress.com/
 */

/*
 * Everything I build is free. All Rights Reserved.
 * A singing computer scientist.
 * Find me on Google.
 */

package com.poscoict.fcmsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FCMSample";
    String str;
    TextView textView;

    Intent intent;
    IntentFilter intentFilter;

    BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();
        Log.i(TAG, "@@ onCreate : " + intent.getStringExtra("push_content"));
        if (intent.getStringExtra("push_content") != null) {
            Toast.makeText(this, "Push message received : ", Toast.LENGTH_SHORT).show();
        }



//        intent = getIntent();
//        if (intent.getStringExtra("push_content") != null) {
//            str = intent.getStringExtra("push_content");
//            Toast.makeText(this, "Push message received : " + str, Toast.LENGTH_SHORT).show();
//        }

//        intentFilter = new IntentFilter();
//        intentFilter.addAction("com.poscoict.fcm.push");
//
//        mReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String str = intent.getStringExtra("push_content");
//                Log.d(TAG, "@@@@ received msg : " + str);
//                Log.d(TAG, "Recieved message by BroadcastReciever: " + intent.getAction());
//                textView = (TextView)findViewById(R.id.txtView1);
//                textView.setText(str);
//            }
//        };
//
//        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
//        unregisterReceiver(mReceiver);
        super.onPause();
    }


}
