package com.udacity.load.app.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.udacity.load.app.detail.DetailActivity
import com.udacity.load.app.domain.model.DetailModel
import com.udacity.load.app.util.Constant

class SnoozeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        intent.extras?.let {
            val data = it.getBundle(Constant.DATA)?.getParcelable<DetailModel>(Constant.DATA)

            val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager
            notificationManager.cancelAll()

            data?.let { detailModel ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(Constant.DATA, detailModel)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

}