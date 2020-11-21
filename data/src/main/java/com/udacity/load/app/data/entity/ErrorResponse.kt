package com.udacity.load.app.data.entity

import com.udacity.load.app.domain.util.ConstantError

data class ErrorResponse(
    var message: String? = ConstantError.ERROR
)