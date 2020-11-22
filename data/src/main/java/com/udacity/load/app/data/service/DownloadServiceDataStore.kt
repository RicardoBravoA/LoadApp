package com.udacity.load.app.data.service

import com.udacity.load.app.data.datastore.DownloadDataStore
import com.udacity.load.app.data.listener.ProgressListener
import com.udacity.load.app.data.mapper.ErrorMapper
import com.udacity.load.app.data.network.ApiManager
import com.udacity.load.app.data.util.FileUtils
import com.udacity.load.app.data.util.RetrofitErrorUtil
import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.util.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DownloadServiceDataStore(private val progressListener: ProgressListener) :
    DownloadDataStore {

    override suspend fun load(url: String, path: String): ResultType<Boolean, ErrorModel> {
        val response = ApiManager.get().load(url)
        delay(1500L)
        return if (response.isSuccessful) {
            val value = path + "/${System.currentTimeMillis()}.zip"
            withContext(Dispatchers.IO) {
                FileUtils.saveFile(
                    response.body(),
                    value,
                    progressListener
                )
            }
            ResultType.Success(true)

        } else {
            val error = RetrofitErrorUtil.parseError(response)!!
            ResultType.Error(ErrorMapper.transformResponseToModel(error))
        }
    }

}