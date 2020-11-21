package com.udacity.load.app.domain.repository

import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.util.ResultType

interface DownloadRepository {

    suspend fun load(
        url: String,
        path: String
    ): ResultType<Boolean, ErrorModel>
}