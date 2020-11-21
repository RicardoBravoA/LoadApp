package com.udacity.load.app.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.load.app.data.repository.DownloadDataRepository
import com.udacity.load.app.domain.usecase.DownloadUseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val downloadUseCase = DownloadUseCase(DownloadDataRepository())
            MainViewModel(
                downloadUseCase
            ) as T

        }
        throw IllegalArgumentException("Unable to construct view model")
    }
}