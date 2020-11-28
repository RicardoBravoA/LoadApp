package com.udacity.load.app.util.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.udacity.load.app.R
import com.udacity.load.app.detail.DetailActivity
import com.udacity.load.app.domain.model.DetailModel
import com.udacity.load.app.util.Constant

fun NotificationManager.sendNotification(detailModel: DetailModel, applicationContext: Context) {

    val contentIntent = Intent(applicationContext, DetailActivity::class.java)
    contentIntent.putExtra(Constant.DATA, detailModel)

    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        Constant.NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val notificationStyle = NotificationCompat.InboxStyle()

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_channel_id)
    ).setSmallIcon(R.drawable.ic_notification)
        .setContentTitle(
            applicationContext
                .getString(R.string.notification_title)
        )
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(notificationStyle)
        .setContentText(detailModel.notificationDescription)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(Constant.NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}