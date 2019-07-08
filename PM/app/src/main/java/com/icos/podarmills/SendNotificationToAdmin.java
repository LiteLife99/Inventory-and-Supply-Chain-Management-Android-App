package com.icos.podarmills;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.icos.podarmills.noti.channelId;

public class SendNotificationToAdmin extends FirebaseMessagingService {
    private NotificationManagerCompat notificationManager;
    String msg;
    Intent intent;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived: received");
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        msg=remoteMessage.getData().get("msg").toString();
        intent = new Intent(this, InventoryList.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notificationManager=NotificationManagerCompat.from(this);
        Notification notification=new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ntclogo)
                .setContentTitle("Maintenance pending notification!")
                .setContentText(msg)
                .setSound(uri)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1,notification);


    }
}

