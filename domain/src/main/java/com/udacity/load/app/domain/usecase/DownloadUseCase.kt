package com.udacity.load.app.domain.usecase

import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.repository.DownloadRepository
import com.udacity.load.app.domain.util.ResultType

class DownloadUseCase(private val downloadRepository: DownloadRepository) {

    suspend fun load(
        url: String
    ): ResultType<Boolean, ErrorModel> {
        return downloadRepository.load(url)
    }
}