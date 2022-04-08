package com.gharbia.medical.firebase;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.gharbia.medical.Activities.SplashActivity;
import com.gharbia.medical.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;
import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "NOTIFICATION_DATA";

    public boolean foregrounded() {
        ActivityManager.RunningAppProcessInfo appProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(appProcessInfo);
        return (appProcessInfo.importance == IMPORTANCE_FOREGROUND || appProcessInfo.importance == IMPORTANCE_VISIBLE);
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("token new ",s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d("wwww", String.valueOf(remoteMessage.getData()));
        Log.d("wwww2", String.valueOf(remoteMessage.getNotification()));
        if (foregrounded()){
            Log.e(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
            Log.e(TAG, "FCM Data Message: " + remoteMessage.getData().toString());

            try {
                JSONObject myJson;
                String title;
                String description;

                myJson = new JSONObject(remoteMessage.getData());

                Log.e(TAG, "FCM myJson: " + myJson.toString());

                title = myJson.optString("title");
                description = myJson.optString("body");

                if (foregrounded()) {
                    Log.e(TAG, "foregrounded");
                } else {
                    Log.e(TAG, "not foregrounded");
                }

                buildNotif(title, description);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Log.e(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
            Log.e(TAG, "FCM Data Message: " + remoteMessage.getData().toString());

            try {
                JSONObject myJson;
                String title;
                String description;

                myJson = new JSONObject(remoteMessage.getData());

                Log.e(TAG, "FCM myJson: " + myJson.toString());

                title = myJson.optString("title");
                description = myJson.optString("body");

                if (foregrounded()) {
                    Log.e(TAG, "foregrounded");
                } else {
                    Log.e(TAG, "not foregrounded");
                }

                buildNotif(title, description);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void buildNotif(String title, String desc) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL);
        Context c = getApplicationContext();
        Intent intent = new Intent(c, SplashActivity.class);
        intent.putExtra("notification", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int num = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(c, num, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher11);
        String channelId = getString(R.string.notification_channel_id);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        NotificationCompat.BigTextStyle notiStyle = new NotificationCompat.BigTextStyle();
        notiStyle.setBigContentTitle(title);
        notiStyle.bigText(desc);

        builder.setContentTitle(title)
                .setContentText(desc)
                .setContentIntent(pendingIntent)
                .setSound(uri)
                .setAutoCancel(true)
                .setStyle(notiStyle)
                .setSmallIcon(R.mipmap.ic_launcher11)
                .setLargeIcon(bitmap1)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(c);
        NotificationManager notificationManager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, getString(R.string.notification_channel_id), NotificationManager.IMPORTANCE_HIGH);
            channel.setLightColor(getColor(R.color.blue));
            mNotificationManager.createNotificationChannel(channel);

        }
        mNotificationManager.notify(num, builder.build());
    }
}