package com.example.tourisme.notification;

import android.app.*;
import android.content.Context;
import android.os.Build;

public class AppNotification  {
    public static  final  String CHANNEL_1_ID="channel1";
    public static  final  String CHANNEL_2_ID="channel2";

    public AppNotification() {
    }

    public void createNotificationChannels(Context context){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notif = new NotificationChannel(CHANNEL_1_ID,"Channel 1", NotificationManager.IMPORTANCE_HIGH);
            notif.setDescription("This is channel 1");

            NotificationChannel notif2 = new NotificationChannel(CHANNEL_2_ID,"Channel 2", NotificationManager.IMPORTANCE_LOW);
            notif2.setDescription("This is channel 2");

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(notif);
            notificationManager.createNotificationChannel(notif2);
        }
    }
}
