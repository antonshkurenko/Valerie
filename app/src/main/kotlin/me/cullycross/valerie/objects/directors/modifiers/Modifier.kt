package me.cullycross.valerie.objects.directors.modifiers

import me.cullycross.valerie.objects.BaseObject

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/27/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
interface Modifier<T : BaseObject> {

    /**
     * to apply with each object in director's cycle after transformation
     */
    fun modify(): (T) -> Unit
}