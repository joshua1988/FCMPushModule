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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 1;
    public static final int SERVICE_DISABLED = 3;

    private static final String TAG = "FCMSample";
    String str = null;
    String message = null;
    TextView textView;

    Intent intent;
    IntentFilter intentFilter;
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "@@ onCreate");

        FirebaseMessaging.getInstance().subscribeToTopic("news");

        intent = getIntent();
        message = intent.getStringExtra("push_content");
        Log.i(TAG, "Push Message is : " + message);
        if (intent.getStringExtra("push_content") != null) {
            Toast.makeText(this, "Push message received : " + message, Toast.LENGTH_LONG).show();
        } else {
            Log.i(TAG, "There is no push message." + message);
        }

        Log.i(TAG, "getOSVersion  : " + getOSVersion());
//        sendPushInfo();

        /*서비스에서 인텐트의 값을 브로드캐스트 리시버로 전달하면 Null Point Exception 오류가 발생한다.
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

    private String getPhoneNumber() {
        String mPhoneNumber = null;
        TelephonyManager telemamanger = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneNumber = telemamanger.getLine1Number();
        Log.i(TAG, "Phone number is : " + mPhoneNumber );
        return mPhoneNumber;
    }

    private String getOSVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        String result = "Android ".concat(release).concat(" (").concat(String.valueOf(sdkVersion)).concat(")");
        return result;
    }

    private String getDeviceId() {
        TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = tManager.getDeviceId();
        return uuid;
    }

    public void sendPushInfo(View v) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "http://192.168.41.79:8080/SmartSafety/restful/fcm-push-service/";
        JsonObjectRequest req = new JsonObjectRequest(URL, generatePushJSON(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "Volley Success : " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Volley Error: " + error.getMessage());
                Log.e(TAG, "Response Error : " + error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            /*@Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }*/
        };
        requestQueue.add(req);
    }

    private JSONObject generatePushJSON() {
        JSONObject pushJSON = new JSONObject();
        try {
            pushJSON.put("device_id", getDeviceId());
            pushJSON.put("device_token", FirebaseInstanceId.getInstance().getToken());
            pushJSON.put("device_os", getOSVersion());
            pushJSON.put("phone_number", getPhoneNumber());
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return pushJSON;
    }
}