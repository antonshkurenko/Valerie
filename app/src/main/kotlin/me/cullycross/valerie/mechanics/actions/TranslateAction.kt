package me.cullycross.valerie.mechanics.actions

import me.cullycross.valerie.mechanics.objects.Translatable
import me.cullycross.valerie.utils.Point
import me.cullycross.valerie.utils.Vector
import me.cullycross.valerie.utils.clamp

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/2/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
// todo(tonyshkurenko), 1/3/16:  should it be open?
// todo(tonyshkurenko), 1/27/16:  add transpolation,
open class TranslateAction(protected val obj: Translatable,
                           protected val to: Point,
                           time: Long = 5000L,
                           callback: Action.Callback? = null) : BaseAction(time, callback) {

    protected lateinit var startPosition: Point;

    protected var angle: Float = 0f;
    protected var distance: Float = 0f;

    override fun start() {
        super.start()
        startPosition = obj.position

        val vector = Vector(startPosition, to)
        angle = vector.angle()
        distance = vector.length()
    }

    override fun onStep(delta: Long) {

        val percent = ((currentTime - startTime).toFloat() / time).clamp(0f, 1f)

        obj.position = startPosition.translate(
                Vector.fromLengthAndAngle((distance * percent).toFloat(), angle))
    }
}