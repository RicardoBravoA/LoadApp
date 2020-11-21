package com.udacity.load.app.data.service

import com.udacity.load.app.data.datastore.DownloadDataStore
import com.udacity.load.app.data.mapper.ErrorMapper
import com.udacity.load.app.data.network.ApiManager
import com.udacity.load.app.data.util.FileUtils
import com.udacity.load.app.data.util.RetrofitErrorUtil
import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.util.ResultType

class DownloadServiceDataStore : DownloadDataStore {

    override suspend fun load(url: String, path: String): ResultType<Boolean, ErrorModel> {
        val response = ApiManager.get().load(url)
        return if (response.isSuccessful) {
            val value = path + "/${System.currentTimeMillis()}.zip"
            FileUtils.saveFile(response.body(), value)
            ResultType.Success(true)

        } else {
            val error = RetrofitErrorUtil.parseError(response)!!
            ResultType.Error(ErrorMapper.transformResponseToModel(error))
        }
    }

}