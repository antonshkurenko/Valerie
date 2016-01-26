package me.cullycross.valerie.objects

import me.cullycross.valerie.utils.Point
import me.cullycross.valerie.utils.Vector

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/22/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

open class BaseRotatableObject(from: Point = Point(0f, 0f),
                               override var angle: Float = Math.PI.toFloat() / 2f,
                               image: Drawable? = null) : BaseObject(from, image), Rotatable {

    override var position: Point = Point(0f, 0f)

    override fun toString(): String {
        return "BaseRotatable: {Position: ${position.toString()}, angle: $angle}"
    }
}