package com.omelchenkoaleks.fitness.notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.omelchenkoaleks.fitness.R
import com.omelchenkoaleks.fitness.utils.APP_ACTIVITY

class AlarmNotificationReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(APP_ACTIVITY, CHANNEL_ID)

        builder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("It's time")
            .setContentText("Time to training")
            .setContentInfo("Info")

        val notificationManager: NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }
}