package me.cullycross.valerie.graphics.renderers

import android.content.Context
import me.cullycross.valerie.graphics.objects.ViewObjectBuilder
import me.cullycross.valerie.graphics.programs.SingleColorProgram
import me.cullycross.valerie.utils.Point
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/10/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class ShuffleRenderer(context: Context) : Abstract2dRenderer(context) {

    private var program: SingleColorProgram? = null

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        super.onSurfaceCreated(gl, config)
        program = SingleColorProgram(context)
    }

    override fun onDrawFrame(gl: GL10) {
        super.onDrawFrame(gl)

        program?.useProgram()

        val drawables =
                ViewObjectBuilder.createCircle(Point(0.5f, 0.5f), 0.15f, 32, aspectRatio).drawableList

        positionObjectInScene(-0.5f, 0.5f)
        program?.setUniforms(modelProjectionMatrix, 1f)

        drawables.forEach {
            it.draw()
        }
    }
}