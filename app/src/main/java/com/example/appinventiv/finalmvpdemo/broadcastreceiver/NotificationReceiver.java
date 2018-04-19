package com.example.appinventiv.finalmvpdemo.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.appinventiv.finalmvpdemo.R;
import com.example.appinventiv.finalmvpdemo.samplehome.HomeActivity;
import com.example.appinventiv.finalmvpdemo.samplenotification.NotificationInfoActivity;

import java.util.Random;

/**
 * Created by appinventiv on 18/4/18.
 */

public class NotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "NotificationReceiver";
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    @Override
    public void onReceive(Context context, Intent intent) {
      NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("Reminders", "Reminders", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setContentTitle("test").setContentText("test").setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Log.i(TAG, "notification sent");
     //  NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(new Random(10).nextInt(), notification.build());
        Toast.makeText(context, "Alarm", Toast.LENGTH_SHORT).show();
        //notificationManager.notify(new Random(1000).nextInt(), notification);

    }
}
