package com.udacity.load.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailModel(
    val description: String,
    val status: Boolean
) : Parcelable