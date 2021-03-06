package com.udacity.load.app.util.resources

import android.content.Context
import com.udacity.load.app.R

class ResourcesProvider(private val context: Context) : ResourcesInterface {

    override fun glideMessage() = context.getString(R.string.select_glide_message)

    override fun loadAppMessage() = context.getString(R.string.select_load_app_message)

    override fun retrofitMessage() = context.getString(R.string.select_retrofit_message)

    override fun glideNotificationMessage() =
        context.getString(R.string.notification_glide_description)

    override fun loadAppNotificationMessage() =
        context.getString(R.string.notification_load_app_description)

    override fun retrofitNotificationMessage() =
        context.getString(R.string.notification_retrofit_description)

    override fun glideNotificationMessageError() =
        context.getString(R.string.notification_glide_description_error)

    override fun loadAppNotificationMessageError() =
        context.getString(R.string.notification_load_app_description_error)

    override fun retrofitNotificationMessageError() =
        context.getString(R.string.notification_retrofit_description_error)

}