package com.udacity.load.app.data.util

import com.udacity.load.app.data.listener.ProgressListener
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ProgressInterceptor(private val listener: ProgressListener) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        return originalResponse.newBuilder()
            .body(originalResponse.body?.let { ProgressResponseBody(it, listener) })
            .build()
    }
}