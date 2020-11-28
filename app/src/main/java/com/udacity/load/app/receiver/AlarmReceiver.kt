package com.udacity.load.app.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.udacity.load.app.util.Constant
import com.udacity.load.app.util.notification.sendNotification

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val bodyMessage = intent.getStringExtra(Constant.BODY_MESSAGE)

        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        bodyMessage?.let {
            notificationManager.sendNotification(
                bodyMessage,
                context
            )
        } ?: notificationManager.sendNotification(
            "default message",
            context
        )

    }

}