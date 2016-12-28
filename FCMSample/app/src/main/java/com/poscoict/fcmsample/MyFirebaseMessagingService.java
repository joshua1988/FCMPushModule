/*
 * Everything I build is free. All Rights Reserved.
 * A singing computer scientist.
 * https://joshuajangblog.wordpress.com/
 */

package com.poscoict.fcmsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by 장 기효 on 2016-12-21.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(TAG, "From: " + remoteMessage.getFrom());
        Log.i(TAG, "@@@@@ Message Received getTitle : " + remoteMessage.getNotification().getTitle());
        Log.i(TAG, "@@@@@ Message Received Notification Message Body : " + remoteMessage.getNotification().getBody());

        // MainActivity
//        Intent mainIntent = new Intent(this, MainActivity.class);
//        mainIntent.putExtra("push_content", remoteMessage.getNotification().getBody());
//        mainIntent.setAction("com.poscoict.fcm.push");
//        Log.i(TAG, "@@@@ Firbase mainintent action : " + mainIntent.getAction());

        // BroadCastReceiver
//        Intent pushIntent = new Intent();
//        pushIntent.putExtra("push_content", remoteMessage.getNotification().getBody());
//        Log.i(TAG, "@@@@ Firbase mainintent action : " + pushIntent.getAction());
//        sendBroadcast(pushIntent);


        // Launch Second Activity with the push message
//        launchSecondActivity(remoteMessage.getNotification().getBody());

        //Calling method to generate notification
        sendNotification(remoteMessage.getNotification().getBody());
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(String messageBody) {
        // Choose which Activity you want to display on the screen (Either Main or Second)
        Intent intent = new Intent(this, MainActivity.class);
//        Intent intent = new Intent(this, SecondActivity.class);

//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("push_content", messageBody);
        intent.putExtra("msg", "text");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Firebase Push Notification")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    /*
    Description : launch a new activity with the push message information.
     */
    private void launchSecondActivity(String messageBody) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("msg", messageBody);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
