/*
 * Everything I build is free. All Rights Reserved.
 * A singing computer scientist.
 * https://joshuajangblog.wordpress.com/
 */

package com.poscoict.fcmsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 장 기효 on 2016-12-22.
 */
public class PushReceiver extends BroadcastReceiver {

    private static final String TAG = "FCMSample";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "@@ BroadcastReceiver");
        String name = intent.getAction();
        Log.i(TAG, "@@ BroadcastReceiver name : " + name);

        // Intent SendBroadCast로 보낸 action TAG 이름으로 필요한 방송을 찾는다.
        if(name.equals("com.poscoict.fcm.push")){
//            Log.i(TAG, "@@@@@@@@@@@ hello" + intent.getStringExtra("push_content"));
            Log.i(TAG, "@@@@@@@@@@@ hello");

//            Toast.makeText(context, "Broadcast Received : " + intent.getStringExtra("push_content") ,Toast.LENGTH_LONG).show();
        }
    }
}
