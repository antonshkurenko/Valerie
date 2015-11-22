package me.cullycross.valerie.objects

import me.cullycross.valerie.objects.Drawable

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/22/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

abstract class BaseObject : Drawable {

    var image: Drawable? = null

    override fun draw() {
        image?.draw()
    }
}