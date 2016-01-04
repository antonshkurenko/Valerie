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

// todo(tonyshkurenko), 1/4/16:  check how to declare only public getter in main constructor
// todo(tonyshkurenko), 1/4/16:  what is internal constructor?
class Line(from: Point, private var end: Point) : BaseObject(from) {

    val length: Float
    val angle: Float

    // todo(tonyshkurenko), 1/4/16:  check how to call this() not in header
    constructor(from: Point, length: Float, angle: Float) :
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

    fun getEnd(): Point {
        return end
    }
}