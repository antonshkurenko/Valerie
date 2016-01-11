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
    /**
     * change setter to translate whole object to position, not only change position
     */
    var position: Point // thanks God, I'm able to declare abstract values!
}