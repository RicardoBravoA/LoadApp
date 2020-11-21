package com.udacity.load.app.data.mapper

import com.udacity.load.app.data.entity.ErrorResponse
import com.udacity.load.app.domain.model.ErrorModel

object ErrorMapper {

    fun transformResponseToModel(errorResponse: ErrorResponse): ErrorModel {
        return ErrorModel(
            errorResponse.message
        )
    }

}