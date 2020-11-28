package com.udacity.load.app.util.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.udacity.load.app.R
import com.udacity.load.app.domain.model.DetailModel
import com.udacity.load.app.receiver.SnoozeReceiver
import com.udacity.load.app.util.Constant

fun NotificationManager.sendNotification(detailModel: DetailModel, applicationContext: Context) {

    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val bundle = Bundle()
    bundle.putParcelable(Constant.DATA, detailModel)
    snoozeIntent.putExtra(
        Constant.DATA,
        bundle
    )

    val snoozePendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        Constant.REQUEST_CODE,
        snoozeIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_id)
    ).setSmallIcon(R.drawable.ic_notification)
        .setContentTitle(
            applicationContext
                .getString(R.string.notification_title)
        )
        .setAutoCancel(true)
        .setContentText(detailModel.notificationDescription)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .addAction(
            R.drawable.ic_next,
            applicationContext.getString(R.string.notification_action),
            snoozePendingIntent
        )

    notify(Constant.NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}