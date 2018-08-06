package com.cere.wsa.common.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cere.wsa.MainActivity;
import com.cere.wsa.R;
import com.cere.wsa.login.LoginActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by user on 8/5/2018.
 */

public class WSAFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "WSAMessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0)
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent  = PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this);
        notiBuilder.setContentTitle("WSA");
        notiBuilder.setContentText(remoteMessage.getNotification().getBody());
        notiBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notiBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notiBuilder.build());
    }


}
