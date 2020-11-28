package com.udacity.load.app.util.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.udacity.load.app.MainActivity
import com.udacity.load.app.R
import com.udacity.load.app.receiver.SnoozeReceiver

private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val notificationImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.ic_notification
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(notificationImage)
        .bigLargeIcon(null)

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        FLAGS
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

        .setStyle(bigPicStyle)
        .setLargeIcon(notificationImage)

        .addAction(
            R.drawable.ic_notification,
            applicationContext.getString(R.string.notification_action),
            snoozePendingIntent
        )
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}