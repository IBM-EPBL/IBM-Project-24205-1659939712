package com.example.covid_19alertapp.extras;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.covid_19alertapp.R;
import com.example.covid_19alertapp.activities.TrackerSettingsActivity;


public abstract class Notifications {

    public static void createNotificationChannel(Context activity) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = activity.getString(R.string.notification_channel_name);
            String description = activity.getString(R.string.notification_channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(Constants.NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Log.d(LogTags.Notification_TAG, "createNotificationChannel: notification channel created");
        }
    }

    public static Notification showNotification(
            int notification_id, Context context, Intent newActivityIntent, boolean notify)
    {

        String title, content;
        PendingIntent pendingIntent = null;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID);

        //start NewActivity on notification tap
        try {
            newActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(context, 0, newActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }catch (NullPointerException e){
            Log.d(LogTags.Notification_TAG, "showNotification: no intent specified");
        }
        switch (notification_id){

            case Constants.PromptTrackerNotification_ID:
                /*
                let user know app is not tracking
                pushing notification button will start tracking
                    */

                title = context.getString(R.string.promptToTrackNotificationTitle);
                content = context.getString(R.string.promptToTrackNotificationContent);

                //build notification
                builder.setContentTitle(title)
                        .setContentText(content)

                        .setSmallIcon(R.drawable.ic_location_off)

                        .setPriority(NotificationCompat.PRIORITY_HIGH)

                        // Intent(TrackerSettingsActivity) that will start when the user taps the button
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                Log.d(Constants.NOTIFICATION_CHANNEL_ID, "PromptToTrackNotification: notification builder created");

                break;

            case Constants.TrackingLocationNotification_ID:
                /*
                shows notification when app is actively tracking user
                    */

                title = context.getString(R.string.trackingNotificationTitle);
                content = context.getString(R.string.trackingNotificationContent);

                //build notification
                builder.setContentTitle(title)
                        .setContentText(content)
                        .setSmallIcon(R.drawable.ic_location_on)
                        .setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.appicon))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        // Intent(Activity) that will start when the user taps the button
                        .setContentIntent(pendingIntent)
                        .setOngoing(true)
                        .setAutoCancel(false);

                Log.d(LogTags.Notification_TAG, "TrackingNotification: notification builder created");

                break;

            case Constants.DangerNotification_ID:
                /*
                show danger notification on infectedLocation visit
                 */

                title = context.getString(R.string.dangerNotificationTitle);
                content = context.getString(R.string.dangerNotificationContent);

                // build notification
                builder.setContentTitle(title)
                        .setContentText(content)
                        .setSmallIcon(R.drawable.ic_infected_location)
                        .setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_notification))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        // Intent(Activity) that will start when the user taps the button
                        .setContentIntent(pendingIntent)
                        // play default device notification tone
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setOngoing(true)
                        .setAutoCancel(false);

                Log.d(LogTags.Notification_TAG, "DangerNotification: notification builder created");

                break;


        }


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        Notification notification = builder.build();

        if(notification_id == Constants.TrackingLocationNotification_ID
                || notification_id == Constants.DangerNotification_ID)
        {
            //makes notification persistent
            notification.flags = Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;
        }

        //show notification here for older version and normal notifications
        if(notify)
            notificationManager.notify(notification_id, notification);

        Log.d(LogTags.Notification_TAG, "showNotification: notification showed");

        //return to start on Foreground (for services in newer version)
        return notification;

    }

    public static void removeNotification(int notification_id, Context context){
        /*
        removes the notification
         */

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(notification_id);
    }

}
