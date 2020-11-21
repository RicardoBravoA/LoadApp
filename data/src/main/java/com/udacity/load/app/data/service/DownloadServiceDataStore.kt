package com.udacity.load.app.data.service

import com.udacity.load.app.data.datastore.DownloadDataStore
import com.udacity.load.app.data.mapper.ErrorMapper
import com.udacity.load.app.data.network.ApiManager
import com.udacity.load.app.data.util.FileUtils.saveToFile
import com.udacity.load.app.data.util.RetrofitErrorUtil
import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.util.ResultType

class DownloadServiceDataStore : DownloadDataStore {

    override suspend fun load(url: String): ResultType<Boolean, ErrorModel> {
        val response = ApiManager.get().load(url)
        return if (response.isSuccessful) {
            response.body()?.byteStream()?.saveToFile("${System.currentTimeMillis()}.zip")
            ResultType.Success(true)

        } else {
            val error = RetrofitErrorUtil.parseError(response)!!
            ResultType.Error(ErrorMapper.transformResponseToModel(error))
        }
    }

}