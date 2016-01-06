package me.cullycross.valerie.objects

import me.cullycross.valerie.utils.Point

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/4/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
interface Translatable {
    // todo(tonyshkurenko), 1/7/16: i want to make its setter private, to set it via translate?
    var position: Point // thanks God, I'm able to declare abstract values!
    fun translate(to: Point)
}