package com.udacity.load.app.data.service

import android.util.Log
import com.udacity.load.app.data.datastore.DownloadDataStore
import com.udacity.load.app.data.listener.ProgressListener
import com.udacity.load.app.data.mapper.ErrorMapper
import com.udacity.load.app.data.network.ApiManager
import com.udacity.load.app.data.util.FileUtils
import com.udacity.load.app.data.util.RetrofitErrorUtil
import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.util.ResultType

class DownloadServiceDataStore(private val progressListener: ProgressListener) :
    DownloadDataStore {

    override suspend fun load(url: String, path: String): ResultType<Boolean, ErrorModel> {
        val response = ApiManager.get().load(url)
        return if (response.isSuccessful) {
            val value = path + "/${System.currentTimeMillis()}.zip"
            Log.i("z- load", response.body()?.contentLength().toString())
            FileUtils.saveFile(
                response.body(),
                value,
                progressListener
            )
            ResultType.Success(true)

        } else {
            val error = RetrofitErrorUtil.parseError(response)!!
            ResultType.Error(ErrorMapper.transformResponseToModel(error))
        }
    }

}