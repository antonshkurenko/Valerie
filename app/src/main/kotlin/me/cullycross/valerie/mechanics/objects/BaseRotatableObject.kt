package me.cullycross.valerie.mechanics.objects

import me.cullycross.valerie.graphics.utils.positionObjectInScene
import me.cullycross.valerie.utils.Point

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

    override val matrix: FloatArray
        get() = positionObjectInScene(position.x, position.y, angle)

    override fun toString() = "BaseRotatable: {Position: ${position.toString()}, angle: $angle}"
}