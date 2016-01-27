package me.cullycross.valerie.mechanics.directors

import me.cullycross.valerie.mechanics.objects.BaseObject
import me.cullycross.valerie.utils.Point

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/5/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

// todo(tonyshkurenko), 1/5/16:  direct line, arc, circle, triangle maybe? or even heart etc
interface Director<T: BaseObject> {
    // todo(tonyshkurenko), 1/7/16:  is this good to declare empty lambda? how to use "null"?
    fun direct(position: Point, list: List<T>, modify: (T) -> Unit = {/*do nothing by default*/})
}