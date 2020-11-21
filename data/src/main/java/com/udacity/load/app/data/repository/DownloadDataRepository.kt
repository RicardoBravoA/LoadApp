package com.udacity.load.app.data.repository

import com.udacity.load.app.data.service.DownloadServiceDataStore
import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.repository.DownloadRepository
import com.udacity.load.app.domain.util.ResultType

class DownloadDataRepository : DownloadRepository {

    override suspend fun load(url: String, path: String): ResultType<Boolean, ErrorModel> {
        val downloadServiceDataStore = DownloadServiceDataStore()
        return downloadServiceDataStore.load(url, path)
    }

}