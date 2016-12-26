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
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 1;
    public static final int SERVICE_DISABLED = 3;

    private static final String TAG = "FCMSample";
    String str = null;
    TextView textView;

    Intent intent;
    IntentFilter intentFilter;
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "@@ onCreate");

        intent = getIntent();
        Log.i(TAG, "Push Message is : " + intent.getStringExtra("push_content"));
        if (intent.getStringExtra("push_content") != null) {
            Toast.makeText(this, "Push message received : ", Toast.LENGTH_LONG).show();
        }

        getDeviceInfo();

        /*서비스에서 인텐트의 값을 브로드캐스트 리시비로 전달하면 Null Point Excepsion 오류가 발생한다.
        아무래도 안드로이드의 액티비티 콜 스택을 제대로 이해하지 못한 상태에서, intent 갱신이나
        추가하는 부분에 있어 기존 액티비티와 충돌하는 걸로 간주된다.
        PendingIntent 를 이용해 intent 의 값을 전달하는 것이 더 정석인 것 같은데
        안드로이드 컴포넌트에 대한 이해가 충분하지 못하여, 실수를 범했다.*/

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "@@ onNewIntent");

        str = intent.getStringExtra("push_content");
        if (str != null) {
            textView = (TextView) findViewById(R.id.txtView1);
            textView.setText("Hello World is done with this push message : " + str);
        } else {
            Toast.makeText(this, "Push Message has not come yet", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "@@ onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void getDeviceInfo() {
        getPhoneNumber();
        getOSVersion();
    }

    private void getPhoneNumber() {
        TelephonyManager telemamanger = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber  = telemamanger.getLine1Number();
        Log.i(TAG, "Phone number is : " + mPhoneNumber );
    }

    private void getOSVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        Log.i(TAG, "Android SDK : " + sdkVersion + " (" + release +")");
    }

}