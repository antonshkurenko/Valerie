package me.cullycross.valerie.objects.directors

import me.cullycross.valerie.objects.BaseObject
import me.cullycross.valerie.utils.Point

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/6/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class HorizontalLineDirector<T : BaseObject>(val distance: Float) : Director<T> {

    override fun direct(position: Point, list: List<T>) {
        val count = list.size

        var startPoint: Point
        if (count % 2 == 1) {
            startPoint = Point(position.x - distance * (count / 2f), position.y)
        } else {
            startPoint = Point(position.x - distance * (count / 2f + .5f), position.y)
        }

        list.forEach {
            it.translate(startPoint)
            startPoint = startPoint.translateX(distance)
        }
    }
}