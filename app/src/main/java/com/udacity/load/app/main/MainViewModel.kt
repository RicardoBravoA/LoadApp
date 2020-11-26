package com.udacity.load.app.main

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.load.app.domain.model.ItemModel
import com.udacity.load.app.domain.usecase.DownloadUseCase
import com.udacity.load.app.domain.util.ResultType
import com.udacity.load.app.util.Constant
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

    fun load(url: String) {
        viewModelScope.launch {
            try {
                when (val result = downloadUseCase.load(
                    url,
                    getApplication<Application>().filesDir.absolutePath
                )) {
                    is ResultType.Success -> {
                        Log.i("z- load", "success")
                    }
                    is ResultType.Error -> {
                        Log.i("z- load", "error")
                    }
                }
            } catch (e: Exception) {
                Log.i("z- load", "Error")
            }
        }
    }

    fun getData(context: Context): List<ItemModel> {
        val list = mutableListOf<ItemModel>()

        list.add(ItemModel(1, resourcesProvider.glideMessage(), Constant.GLIDE_URL))
        list.add(ItemModel(2, resourcesProvider.loadAppMessage(), Constant.LOAD_APP_URL))
        list.add(ItemModel(3, resourcesProvider.retrofitMessage(), Constant.RETROFIT_URL))

        return list
    }

}