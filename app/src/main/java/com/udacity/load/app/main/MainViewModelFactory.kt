package com.udacity.load.app.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.load.app.data.listener.ProgressListener
import com.udacity.load.app.data.repository.DownloadDataRepository
import com.udacity.load.app.domain.usecase.DownloadUseCase
import com.udacity.load.app.util.resources.ResourcesProvider

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")

            val downloadUseCase = DownloadUseCase(DownloadDataRepository())
            val resourcesProvider = ResourcesProvider(app)

            return MainViewModel(app, downloadUseCase, resourcesProvider) as T
        }
        throw IllegalArgumentException("Unable to construct view model")
    }
}