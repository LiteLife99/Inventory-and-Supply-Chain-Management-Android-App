package com.icos.podarmills;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class noti extends Application {

    public static final String channelId="channel";

    @Override
    public void onCreate(){
        super.onCreate();

        createNotificationChannel();
    }
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel=new NotificationChannel(
                    channelId,
                    "channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("this is channel");
            Log.d("androidV","Latest android version");
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
