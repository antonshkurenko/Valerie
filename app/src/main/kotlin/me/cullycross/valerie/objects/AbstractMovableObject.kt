package me.cullycross.valerie.objects

import utils.Point
import utils.Vector
import java.text.FieldPosition

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/22/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

abstract class AbstractMovableObject(var position: Point, var speed: Vector): BaseObject(), Movable {

    override fun move() {
        position = position.translate(speed)
    }

    override fun stop() {
        speed = Vector(0f, 0f)
    }
}