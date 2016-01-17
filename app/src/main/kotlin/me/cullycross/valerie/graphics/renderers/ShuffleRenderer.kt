package me.cullycross.valerie.graphics.renderers

import android.content.Context
import graphics.utils.VertexArray
import me.cullycross.valerie.graphics.objects.ViewObjectBuilder
import me.cullycross.valerie.graphics.programs.SingleColorProgram
import me.cullycross.valerie.graphics.utils.POSITION_COMPONENT_COUNT
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

        val data = ViewObjectBuilder.createCircle(Point(0f, 0f), 0.15f, 32, aspectRatio)

        val drawables =
                data.drawableList

        val vertexArray = VertexArray(data.vertexData)

        positionObjectInScene(0f, 0f)

        program?.setUniforms(modelProjectionMatrix, 1f)

        if (program != null) {

            val positionLocation = program?.positionLocation
            if (positionLocation != null) {
                vertexArray.setVertexAttribPointer(0, positionLocation,
                        POSITION_COMPONENT_COUNT, 0);
            }
        }

        drawables.forEach {
            it.draw()
        }
    }
}