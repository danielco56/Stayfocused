package com.example.danielco56.stayfocused;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.annotation.RequiresApi;

import java.lang.annotation.Target;

@RequiresApi(26)
public class NotificationHelper extends ContextWrapper {

    private NotificationManager manager;
    public static final String PRIMARY_CHANNEL_NAME = "default";
    public static final String PRIMARY_CHANNEL_ID = "id";

    public NotificationHelper(Context base) {
        super(base);
        createChannel();
    }


    private void createChannel() {

        NotificationChannel myChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, PRIMARY_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        myChannel.enableLights(true);
        myChannel.enableVibration(true);
        myChannel.setLightColor(Color.GREEN);
        myChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(myChannel);

    }

    public NotificationManager getManager() {
        if (manager == null)
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        return manager;
    }


    public Notification.Builder getMyChannelNotification(String body) {

            return new Notification.Builder(getApplicationContext(), PRIMARY_CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setContentTitle("Notificare Dă-i Groapă")
                    .setVibrate(new long[]{ 1000, 1000, 1000, 1000, 1000 })
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentText(body)
                    .setPriority(Notification.PRIORITY_MAX);

    }
}
