package com.udacity.load.app.domain.repository

import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.util.ResultType
import okhttp3.ResponseBody
import retrofit2.Response

interface DownloadRepository {

    suspend fun load(
        url: String
    ): ResultType<Response<ResponseBody>, ErrorModel>
}