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

class Line(from: Point, private var end: Point) : BaseObject(from) {

    val length: Float
    val angle: Float

    constructor(from: Point = Point(0f, 0f),
                length: Float = 1f,
                angle: Float = Math.PI.toFloat() / 2f) :
        this(from, from.translate(Vector.fromLengthAndAngle(length, angle)))


    init {
        val vector = Vector(from, end)
        length = vector.length()
        angle = vector.angle()
    }

    override fun translate(to: Point) {
        position = to
        end = position.translate(Vector.fromLengthAndAngle(length, angle))
    }

    override fun toString(): String {
        return "{Point: ${position.toString()}, length: $length, angle: $angle}"
    }

    fun getEnd(): Point {
        return end
    }
}