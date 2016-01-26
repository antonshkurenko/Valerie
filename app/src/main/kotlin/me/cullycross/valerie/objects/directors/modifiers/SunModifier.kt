package me.cullycross.valerie.objects.directors.modifiers

import me.cullycross.valerie.objects.BaseRotatableObject
import me.cullycross.valerie.utils.Point
import me.cullycross.valerie.utils.Vector

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/27/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

/**
 * Applies modification to the rotatable objects
 * rotating them according to the center position
 */
class SunModifier(val center: Point = Point(y = -0.5f)) : Modifier<BaseRotatableObject> {
    override fun modify(): (BaseRotatableObject) -> Unit {
        return {
            it.angle = Vector(center, it.position).angle()
        }
    }
}