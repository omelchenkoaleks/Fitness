package com.omelchenkoaleks.fitness.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

const val CHANNEL_ID = "com.omelchenkoaleks.fitness"
const val CHANNEL_NAME = "Android Channel"

class NotificationUtils(base: Context?) : ContextWrapper(base) {

    private lateinit var manager: NotificationManager

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.GREEN
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getManager().createNotificationChannel(channel)
    }

    private fun getManager(): NotificationManager {
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager
    }
}