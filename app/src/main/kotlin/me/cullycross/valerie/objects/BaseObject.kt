package me.cullycross.valerie.objects

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
        var image: Drawable? = null) : Drawable, Translatable {

    override fun draw() {
        image?.draw()
    }
}