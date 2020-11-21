package com.udacity.load.app.data.util

import com.udacity.load.app.data.entity.ErrorResponse
import com.udacity.load.app.data.network.ApiManager
import retrofit2.Response
import java.io.IOException

object RetrofitErrorUtil {
    fun parseError(response: Response<*>): ErrorResponse? {

        val converter = ApiManager.retrofit.responseBodyConverter<ErrorResponse>(
            ErrorResponse::class.java,
            arrayOfNulls<Annotation>(0)
        )

        val error: ErrorResponse

        try {
            error = converter.convert(response.errorBody()!!)!!
        } catch (e: IOException) {
            return ErrorResponse()
        }

        return error
    }
}