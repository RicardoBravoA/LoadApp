package com.udacity.load.app.domain.model

import com.udacity.load.app.domain.util.ConstantError

data class ErrorModel(
    var message: String? = ConstantError.ERROR
)