package com.udacity.load.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemModel(
    val id: Int,
    val description: String,
    val url: String,
    val notificationDescription: String,
    val notificationDescriptionError: String
) : Parcelable