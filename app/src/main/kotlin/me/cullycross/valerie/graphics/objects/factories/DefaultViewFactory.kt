package me.cullycross.valerie.graphics.objects.factories

import me.cullycross.valerie.graphics.objects.ViewObjectBuilder
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

    private val lineInstance: ViewObjectBuilder.GeneratedData by lazy {
        ViewObjectBuilder.createLine(to = Point(y = 0.1f), aspectRatio = aspectRatio)
    }

    private val circleInstance: ViewObjectBuilder.GeneratedData by lazy {
        ViewObjectBuilder.createCircle(Point(), 0.02f, 32, aspectRatio)
    }

    override fun getLine(): ViewObjectBuilder.GeneratedData {
        return lineInstance
    }

    override fun getCircle(): ViewObjectBuilder.GeneratedData {
        return circleInstance
    }
}