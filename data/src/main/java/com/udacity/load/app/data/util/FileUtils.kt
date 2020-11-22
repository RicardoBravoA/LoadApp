package com.udacity.load.app.data.util

import android.util.Log
import com.udacity.load.app.data.listener.ProgressListener
import okhttp3.ResponseBody
import java.io.*

object FileUtils {

    fun saveFile(
        body: ResponseBody?,
        path: String,
        progressListener: ProgressListener
    ): String? {
        if (body == null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val totalSizeString = body.contentLength().toString()
            Log.i("z- totalSizeString", totalSizeString)
            val totalSize = totalSizeString.toLong()
            Log.i("z- totalSize", totalSize.toString())
            val fos = FileOutputStream(path)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                var actualSize = 0L
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                    actualSize += read
                    Log.i("z- data", "$actualSize - $totalSize")
                    progressListener.progress(calculateProgress(actualSize, totalSize))
                }
                output.flush()
                progressListener.progress(calculateProgress(totalSize, totalSize))
            }
            Log.i("z- path", path)
            return path
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            input?.close()
        }
        return null
    }


    private fun calculateProgress(actualSize: Long, totalSize: Long): Int {
        val progress = (100 * actualSize / totalSize).toInt()
        Log.i("z- calculateProgress", progress.toString())
        return progress
    }

}