package com.udacity.load.app.data.datastore

import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.util.ResultType

interface DownloadDataStore {

    suspend fun load(
        url: String
    ): ResultType<Boolean, ErrorModel>
}