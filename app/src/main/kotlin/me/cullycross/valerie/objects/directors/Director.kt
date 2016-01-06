package me.cullycross.valerie.objects.directors

import me.cullycross.valerie.objects.BaseObject
import me.cullycross.valerie.utils.Point

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/5/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

// todo(tonyshkurenko), 1/5/16:  direct line, circle, triangle maybe? or even heart etc
interface Director<T: BaseObject> {
    fun direct(position: Point, list: List<T>)
}