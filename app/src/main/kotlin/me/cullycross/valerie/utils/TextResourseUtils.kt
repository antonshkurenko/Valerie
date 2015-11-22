package utils

import android.content.Context
import android.content.res.Resources
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

fun readTextFileFromResource(context: Context, resourceId: Int): String {
    val body = StringBuilder()

    try {
        val inputStream = context.resources.openRawResource(resourceId)
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)

        var nextLine: String = bufferedReader.readLine()

        while (nextLine != null) {
            body.append(nextLine)
            body.append('\n')
            nextLine = bufferedReader.readLine()
        }
    } catch (e: IOException) {
        throw RuntimeException("Could not open resource: " + resourceId, e)
    } catch (nfe: Resources.NotFoundException) {
        throw RuntimeException("Resource not found: " + resourceId, nfe)
    }

    return body.toString()
}
