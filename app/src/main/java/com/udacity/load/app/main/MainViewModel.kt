package com.udacity.load.app.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.load.app.domain.usecase.DownloadUseCase
import com.udacity.load.app.domain.util.ResultType
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application, private val downloadUseCase: DownloadUseCase) :
    AndroidViewModel(application) {

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

}