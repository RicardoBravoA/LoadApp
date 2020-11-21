package com.udacity.load.app.data.util

import android.util.Log
import okhttp3.ResponseBody
import java.io.*


object FileUtils {

    fun saveFile(body: ResponseBody?, path: String): String {
        if (body == null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val fos = FileOutputStream(path)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            Log.i("z- path", path)
            return path
        } catch (e: Exception) {
            Log.e("z- saveFile", e.toString())
        } finally {
            input?.close()
        }
        return "z- no path"
    }


    private fun calculateProgress(totalSize: Double, downloadSize: Double): Double {
        return ((downloadSize / totalSize) * 100)
    }

}