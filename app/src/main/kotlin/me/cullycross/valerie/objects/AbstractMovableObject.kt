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

open class AbstractMovableObject(position: Point, var speed: Vector) : BaseObject(position), Movable {

    override fun move() {
        position = position.translate(speed)
    }

    override fun stop() {
        speed = Vector(0f, 0f)
    }
}