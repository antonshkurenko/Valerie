package me.cullycross.valerie.graphics.objects

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/26/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
interface ViewFactory {
    fun createLine(): ViewObjectBuilder.GeneratedData
    fun createCircle(): ViewObjectBuilder.GeneratedData
}