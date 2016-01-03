package me.cullycross.valerie.objects.actions

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/2/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
interface Action {
    fun start()
    fun step(delta: Long)
    fun isActive(): Boolean

    interface Callback {
        fun onActionStart()
        fun onActionEnd()
    }
}