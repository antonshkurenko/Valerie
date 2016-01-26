package me.cullycross.valerie.graphics.objects

import me.cullycross.valerie.utils.Point

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/26/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

/**
 * It holds one copy of default object picture. Kind of lazy flyweight
 */
class DefaultViewFactory(val aspectRatio: Float): ViewFactory {

    private val line: ViewObjectBuilder.GeneratedData by lazy {
        ViewObjectBuilder.createLine(to = Point(y = 0.1f), aspectRatio = aspectRatio)
    }

    private val circle: ViewObjectBuilder.GeneratedData by lazy {
        ViewObjectBuilder.createCircle(Point(), 0.02f, 32, aspectRatio)
    }

    override fun createLine(): ViewObjectBuilder.GeneratedData {
        return line
    }

    override fun createCircle(): ViewObjectBuilder.GeneratedData {
        return circle
    }
}