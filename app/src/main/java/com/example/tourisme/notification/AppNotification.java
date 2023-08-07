package com.example.tourisme.notification;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.tourisme.AcceuilActivity;
import com.example.tourisme.R;

public class AppNotification  {
    public static  final  String CHANNEL_1_ID="channel1";
    public static  final  int CHAN_1_ID=1;
    public static  final  String CHANNEL_2_ID="channel2";
    public static  final  int CHAN_2_ID=2;

    public static  final  String CHANNEL_3_ID="channel3";
    public static  final  int CHAN_3_ID=3;

    public AppNotification() {
    }

    public void createNotificationChannels(Context context){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notif = new NotificationChannel(CHANNEL_1_ID,"Channel 1", NotificationManager.IMPORTANCE_HIGH);
            notif.setDescription("This is channel 1");

            NotificationChannel notif2 = new NotificationChannel(CHANNEL_2_ID,"Channel 2", NotificationManager.IMPORTANCE_LOW);
            notif2.setDescription("This is channel 2");

            NotificationChannel notif3 = new NotificationChannel(CHANNEL_3_ID,"Channel 3", NotificationManager.IMPORTANCE_LOW);
            notif3.setDescription("This is channel 3");

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(notif);
            notificationManager.createNotificationChannel(notif2);
            notificationManager.createNotificationChannel(notif3);

        }
    }

    public void showNotification(Context context,String name_exist) {

        if(name_exist!=null){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.logo_notification)
                    .setContentTitle("Coucou "+name_exist)
                    .setContentText("Bienvenu Sur AnkarA")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setAutoCancel(true);

            Intent intent = new Intent(context, AcceuilActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Utilisez FLAG_IMMUTABLE ici
            );

            builder.setContentIntent(pendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(CHAN_1_ID, builder.build());
        }

    }

    public void showNotificationRegsitre(Context context,String name_exist) {

        if(name_exist!=null){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.logo_notification)
                    .setContentTitle("Inscription terminer ")
                    .setContentText("Bienvenu Sur la  l'application "+name_exist)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setAutoCancel(true);

            Intent intent = new Intent(context, AcceuilActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Utilisez FLAG_IMMUTABLE ici
            );

            builder.setContentIntent(pendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(CHAN_2_ID, builder.build());
        }

    }

    public void showNotificationUpdate(Context context,String name_exist) {

        if(name_exist!=null){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.logo_notification)
                    .setContentTitle("Modification des informations")
                    .setContentText("Mise à jour des informations termminer")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setAutoCancel(true);

            Intent intent = new Intent(context, AcceuilActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Utilisez FLAG_IMMUTABLE ici
            );

            builder.setContentIntent(pendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(CHAN_3_ID, builder.build());
        }

    }
}
