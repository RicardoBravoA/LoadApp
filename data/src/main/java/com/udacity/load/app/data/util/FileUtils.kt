package com.udacity.load.app.data.util

import okhttp3.ResponseBody
import java.io.*

object FileUtils {

    fun saveFile(
        body: ResponseBody?,
        path: String
    ): String? {
        if (body == null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val fos = FileOutputStream(path)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                var actualSize = 0L
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                    actualSize += read
                }
                output.flush()
            }
            return path
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            input?.close()
        }
        return null
    }


    fun calculateProgress(actualSize: Long, totalSize: Long): Int {
        return (100 * actualSize / totalSize).toInt()
    }

}