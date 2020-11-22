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
    ): String {
        if (body == null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val fos = FileOutputStream(path)
            fos.use { output ->
                val buffer = ByteArray(8 * 1024)
                var read: Int
                var actualSize = 0L
                val totalSize = body.contentLength()
                Log.i("z- totalSize", totalSize.toString())
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                    actualSize += read
                    Log.i("z- actualSize", actualSize.toString())
                    progressListener.progress(calculateProgress(actualSize, totalSize))
                }
                output.flush()
                progressListener.progress(calculateProgress(totalSize, totalSize))
            }
            Log.i("z- path", path)
            return path
            /*val file = File(path, name)
            Log.i("z- totalSize", file.length().toString())
            body.byteStream().use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    val data = ByteArray(4 * 1024)
                    var read: Int
                    var actualSize = 0L
                    val totalSize = body.contentLength()
                    Log.i("z- totalSize", totalSize.toString())
                    while (inputStream.read(data).also { read = it } != -1) {
                        outputStream.write(data, 0, read)
                        actualSize += read
                        Log.i("z- actualSize", actualSize.toString())
                        progressListener.progress(calculateProgress(actualSize, totalSize))
                    }
                    Log.i("z- totalSize", totalSize.toString())
                    progressListener.progress(calculateProgress(totalSize, totalSize))
                }
            }*/
        } catch (e: Exception) {
            Log.e("z- saveFile", e.toString())
        } finally {
            input?.close()
        }
        return "z- no path"
    }


    private fun calculateProgress(actualSize: Long, totalSize: Long): Long {
        return ((actualSize / totalSize) * 100)
    }

}