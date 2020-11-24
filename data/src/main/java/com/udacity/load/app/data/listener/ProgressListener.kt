package com.udacity.load.app.data.listener

interface ProgressListener {

    fun load(progress: Int, contentLength: Long)
}