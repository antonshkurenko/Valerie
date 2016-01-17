package me.cullycross.valerie.objects

import me.cullycross.valerie.utils.Point

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/17/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class Circle(position: Point = Point(),
             val radius: Float,
             image: Drawable? = null) : BaseObject(position, image) {

    override fun toString(): String {
        return "Circle: {Position ${position.toString()}, radius: $radius}"
    }
}