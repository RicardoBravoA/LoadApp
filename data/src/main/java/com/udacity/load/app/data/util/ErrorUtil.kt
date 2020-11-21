package com.udacity.load.app.data.util

import com.udacity.load.app.domain.model.ErrorModel
import com.udacity.load.app.domain.util.ConstantError

object ErrorUtil {

    fun errorHandler(error: Throwable): ErrorModel {

        val errorException: RetrofitException =
            if (error is RetrofitException) {
                error
            } else {
                RetrofitException.retrofitException(error)
            }

        return when (errorException.kind) {
            RetrofitException.Kind.HTTP -> errorException.getErrorBodyAs(ErrorModel::class.java)!!
            RetrofitException.Kind.NETWORK -> ErrorModel()
            else -> ErrorModel(ConstantError.ERROR)
        }
    }
}