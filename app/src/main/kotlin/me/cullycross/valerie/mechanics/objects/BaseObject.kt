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

open class BaseObject(
        override var position: Point = Point(0f, 0f),
        var image: Drawable? = null) : Translatable {

    open val matrix: FloatArray
        get() = positionObjectInScene(position.x, position.y)

    fun draw() = image?.draw(this)
}