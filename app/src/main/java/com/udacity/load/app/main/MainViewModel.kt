package com.udacity.load.app.main

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.load.app.domain.model.DetailModel
import com.udacity.load.app.domain.model.ItemModel
import com.udacity.load.app.domain.usecase.DownloadUseCase
import com.udacity.load.app.domain.util.ResultType
import com.udacity.load.app.receiver.AlarmReceiver
import com.udacity.load.app.util.Constant
import com.udacity.load.app.util.notification.cancelNotifications
import com.udacity.load.app.util.resources.ResourcesProvider
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    application: Application,
    private val downloadUseCase: DownloadUseCase,
    private val resourcesProvider: ResourcesProvider
) :
    AndroidViewModel(application) {

    private val _itemList = MutableLiveData<List<ItemModel>>()
    val itemList: LiveData<List<ItemModel>>
        get() = _itemList

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    private lateinit var notifyPendingIntent: PendingIntent

    private val alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    init {
        getData()
    }

    fun load(itemModel: ItemModel) {
        viewModelScope.launch {
            try {
                when (downloadUseCase.load(
                    itemModel.url,
                    getApplication<Application>().filesDir.absolutePath
                )) {
                    is ResultType.Success -> {
                        _success.value = true

                        showNotification(
                            DetailModel(
                                itemModel.description,
                                itemModel.notificationDescription,
                                false
                            )
                        )

                    }
                    is ResultType.Error -> {
                        _success.value = false
                        showNotification(
                            DetailModel(
                                itemModel.description,
                                itemModel.notificationDescription,
                                false
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _success.value = false
                showNotification(
                    DetailModel(
                        itemModel.description,
                        itemModel.notificationDescription,
                        false
                    )
                )
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            val list = mutableListOf<ItemModel>()

            list.add(
                ItemModel(
                    1,
                    resourcesProvider.glideMessage(),
                    Constant.GLIDE_URL,
                    resourcesProvider.glideNotificationMessage()
                )
            )
            list.add(
                ItemModel(
                    2,
                    resourcesProvider.loadAppMessage(),
                    Constant.LOAD_APP_URL,
                    resourcesProvider.loadAppNotificationMessage()
                )
            )
            list.add(
                ItemModel(
                    3,
                    resourcesProvider.retrofitMessage(),
                    Constant.RETROFIT_URL,
                    resourcesProvider.retrofitNotificationMessage()
                )
            )

            _itemList.value = list
        }
    }

    private fun showNotification(detailModel: DetailModel) {
        val notifyIntent = Intent(getApplication(), AlarmReceiver::class.java)
        notifyIntent.putExtra(
            Constant.DATA,
            detailModel
        )

        notifyPendingIntent = PendingIntent.getBroadcast(
            getApplication(),
            Constant.REQUEST_CODE,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val triggerTime = SystemClock.elapsedRealtime()

        val notificationManager =
            ContextCompat.getSystemService(
                getApplication(),
                NotificationManager::class.java
            ) as NotificationManager
        notificationManager.cancelNotifications()

        AlarmManagerCompat.setExactAndAllowWhileIdle(
            alarmManager,
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,
            notifyPendingIntent
        )
    }

}