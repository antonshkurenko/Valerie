package me.cullycross.valerie.graphics.objects.factories

import me.cullycross.valerie.graphics.objects.ViewObjectBuilder

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/26/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
interface ViewFactory {
    fun getLine(): ViewObjectBuilder.GeneratedData
    fun getCircle(): ViewObjectBuilder.GeneratedData
}