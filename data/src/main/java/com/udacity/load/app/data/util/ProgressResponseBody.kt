package com.udacity.load.app.data.util

import com.udacity.load.app.data.listener.ProgressListener
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import java.io.IOException

class ProgressResponseBody internal constructor(
    private val responseBody: ResponseBody,
    private val progressListener: ProgressListener
) :
    ResponseBody() {
    private var bufferedSource: BufferedSource? = null
    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun source(): BufferedSource {
        if (bufferedSource == null) {
            bufferedSource = source(responseBody.source()).buffer()
        }
        return bufferedSource as BufferedSource
    }

    private fun source(source: Source): Source {
        return object : ForwardingSource(source) {
            var totalBytesRead = 0L

            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)
                totalBytesRead += if (bytesRead != -1L) bytesRead else 0

                val totalSize = responseBody.contentLength()

                progressListener.load(
                    FileUtils.calculateProgress(
                        totalBytesRead,
                        totalSize
                    ), totalSize
                )
                return bytesRead
            }
        }
    }
}