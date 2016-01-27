package me.cullycross.valerie.mechanics.actions

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/2/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
abstract class BaseAction(protected val time: Long,
                          protected val callback: Action.Callback? = null) : Action {

    protected var startTime: Long = -1L
    protected var currentTime: Long = 0L

    protected abstract fun onStep(delta: Long);

    override fun start() {
        callback?.onActionStart()

        startTime = System.currentTimeMillis()
        currentTime = startTime
    }

    override fun step(delta: Long) {

        if (delta < 0) {
            throw IllegalArgumentException("Delta can not be < 0")
        }

        if (!isActive()) {
            throw IllegalStateException("Action is not started!")
        }

        currentTime += delta

        onStep(delta);

        if (!isActive()) {
            callback?.onActionEnd()
        }
    }

    override fun isActive() = startTime != -1L && currentTime - startTime < time

}