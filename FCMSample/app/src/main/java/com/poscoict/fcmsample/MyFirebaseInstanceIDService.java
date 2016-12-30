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

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by 장 기효 on 2016-12-21.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FCMSample";

    PushMessageVO vo = new PushMessageVO();

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.i(TAG, "Refreshed token: " + refreshedToken);
        vo.setFCM_TOKEN(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }


}
