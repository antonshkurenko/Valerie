package me.cullycross.valerie.mechanics

import android.content.Context
import android.opengl.GLSurfaceView
import timber.log.Timber

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/29/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

class LogicThread(val context: Context,
                  val mechanics: Machinarium,
                  val view: GLSurfaceView) : Thread() {

    private companion object {
        const val TICKS_PER_SECOND = 30
        const val SKIP_TICKS = 1000 / TICKS_PER_SECOND
        const val MAX_FRAMESKIP = 5
    }

    var isRunning = false
        private set

    override fun run() {

        isRunning = true
        var loops: Int

        var nextGameTick = System.currentTimeMillis() // start time
        val startTime = nextGameTick
        var stepTime = nextGameTick

        outterCycle@ while (isRunning) {

            loops = 0;
            while (System.currentTimeMillis() > nextGameTick && loops < MAX_FRAMESKIP) {

                val current = System.currentTimeMillis()
                if (mechanics.step(current - stepTime)) {

                    countInterpolation(nextGameTick)
                    view.requestRender() // draw

                    break@outterCycle // the end, go to the label
                }
                stepTime = current
                nextGameTick += SKIP_TICKS
                loops++
            }

            countInterpolation(nextGameTick)
            view.requestRender() // draw

        }

        val finalTime = java.lang.System.currentTimeMillis() - startTime
        Timber.d("End, final time: %d", finalTime)
    }

    private fun countInterpolation(nextGameTick: Long) =
            (System.currentTimeMillis() + SKIP_TICKS - nextGameTick) / SKIP_TICKS;

    public interface EventsCallback {
        fun onStartGame()

        fun onUpdate(time: Long)

        fun onLostGame(finalTime: Long)

        fun onEndGame()
    }
}