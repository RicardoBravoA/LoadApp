package com.udacity.load.app.util.resources

import android.content.Context
import com.udacity.load.app.R
import com.udacity.load.app.util.resources.ResourcesInterface

class ResourcesProvider(private val context: Context) : ResourcesInterface {

    override fun glideMessage() = context.getString(R.string.select_glide_message)

    override fun loadAppMessage() = context.getString(R.string.select_load_app_message)

    override fun retrofitMessage() = context.getString(R.string.select_retrofit_message)

}