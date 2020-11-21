package com.udacity.load.app.data.util

import java.io.File
import java.io.InputStream

object FileUtils {

    fun InputStream.saveToFile(file: String) = use { input ->
        File(file).outputStream().use { output ->
            input.copyTo(output)
        }
    }

    private fun calculateProgress(totalSize: Double, downloadSize: Double): Double {
        return ((downloadSize / totalSize) * 100)
    }

}