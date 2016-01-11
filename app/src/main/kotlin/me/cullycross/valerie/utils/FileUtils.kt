package me.cullycross.valerie.utils

import android.content.Context
import timber.log.Timber
import java.io.*

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

fun write(ctx: Context, fileName: String,
          content: String) {
    val file: File
    val outputStream: FileOutputStream
    try {
        file = File(ctx.cacheDir, fileName)

        outputStream = FileOutputStream(file)
        outputStream.write(content.toByteArray())
        outputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }

}

fun read(ctx: Context, fileName: String): String {
    val input: BufferedReader
    val file: File
    val builder = StringBuilder("")
    try {
        file = File(ctx.cacheDir, fileName)

        input = BufferedReader(InputStreamReader(FileInputStream(file)))
        var line: String? = input.readLine()
        while(line != null) {
            builder.append(line)
            line = input.readLine()
        }

        Timber.d(builder.toString())
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return builder.toString()
}
