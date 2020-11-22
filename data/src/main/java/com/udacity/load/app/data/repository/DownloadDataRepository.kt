package com.udacity.load.app.data.repository

import com.udacity.load.app.data.listener.ProgressListener
import com.udacity.load.app.data.service.DownloadServiceDataStore
import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.repository.DownloadRepository
import com.udacity.load.app.domain.util.ResultType

class DownloadDataRepository(private val progressListener: ProgressListener) : DownloadRepository {

    override suspend fun load(url: String, path: String): ResultType<Boolean, ErrorModel> {
        val downloadServiceDataStore = DownloadServiceDataStore(progressListener)
        return downloadServiceDataStore.load(url, path)
    }

}