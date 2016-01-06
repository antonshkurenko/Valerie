package me.cullycross.valerie.utils

import java.util.Random

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

class Point(val x: Float, val y: Float) {

    fun translateX(distance: Float): Point {
        return Point(x + distance, y)
    }

    fun translateY(distance: Float): Point {
        return Point(x, y + distance)
    }

    fun translate(distanceX: Float, distanceY: Float): Point {
        return Point(x + distanceX, y + distanceY)
    }

    fun translate(vector: Vector): Point {
        return Point(x + vector.x, y + vector.y)
    }

    fun distanceTo(to: Point): Float {
        return Vector(this, to).length()
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}

class Circle(val center: Point, val radius: Float) {

    fun scale(scale: Float): Circle {
        return Circle(center, radius * scale)
    }

    fun intersects(with: Circle): Boolean {
        return center.distanceTo(with.center) <= radius + with.radius
    }
}

class Vector(val x: Float, val y: Float) {

    constructor(from: Point, to: Point) : this(to.x - from.x, to.y - from.y)

    companion object {
        fun fromLengthAndAngle(length: Float, angle: Float): Vector {
            return Vector(length * Math.sin(angle.toDouble()).toFloat(),
                    length * Math.cos(angle.toDouble()).toFloat())
        }
    }

    fun length(): Float {
        return Math.sqrt(x * x + y * y.toDouble()).toFloat()
    }

    fun dotProduct(other: Vector): Float {
        return x * other.x + y * other.y
    }

    fun scale(f: Float): Vector {
        return Vector(x * f, y * f)
    }

    fun extend2d(add: Float): Vector {
        return Vector(x * (1 + Math.abs(add / x)), y * (1 + Math.abs(add / y)))
    }

    fun add(other: Vector): Vector {
        return Vector(x + other.x, y + other.y)
    }

    fun rotateRandom(r: Random): Vector {
        val angle = r.nextFloat() * 2f * Math.PI.toFloat()
        return rotate(angle)
    }

    fun rotate(angle: Float): Vector {
        return Vector(x * Math.cos(angle.toDouble()).toFloat() - y * Math.sin(angle.toDouble()).toFloat(),
                x * Math.sin(angle.toDouble()).toFloat() + y * Math.cos(angle.toDouble()).toFloat())
    }

    fun angle(): Float {
        return Math.atan2(y.toDouble(), x.toDouble()).toFloat()
    }

    fun point(): Point {
        return Point(x, y)
    }

    /**
     * CCW winding
     */
    fun perpendicularCCW(): Vector {
        return Vector(-y, x)
    }

    /**
     * CW winding
     */
    fun perpendicularCW(): Vector {
        return Vector(y, -x)
    }
}
