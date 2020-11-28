package com.udacity.load.app.util.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.udacity.load.app.MainActivity
import com.udacity.load.app.R
import com.udacity.load.app.receiver.SnoozeReceiver
import com.udacity.load.app.util.Constant

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        Constant.NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val notificationStyle = NotificationCompat.InboxStyle()

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        Constant.REQUEST_CODE,
        snoozeIntent,
        Constant.FLAGS
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_id)
    ).setSmallIcon(R.drawable.ic_notification)
        .setContentTitle(
            applicationContext
                .getString(R.string.notification_title)
        )
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(notificationStyle)
        .addAction(
            R.drawable.ic_notification,
            applicationContext.getString(R.string.notification_action),
            snoozePendingIntent
        )
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(Constant.NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}