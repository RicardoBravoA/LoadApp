package com.udacity.load.app.data.repository

import com.udacity.load.app.data.datastore.DownloadDataStore
import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.repository.DownloadRepository
import com.udacity.load.app.domain.util.ResultType

class DownloadDataRepository(private val downloadDataStore: DownloadDataStore) :
    DownloadRepository {

    override suspend fun load(url: String): ResultType<Boolean, ErrorModel> {
        return downloadDataStore.load(url)
    }

}