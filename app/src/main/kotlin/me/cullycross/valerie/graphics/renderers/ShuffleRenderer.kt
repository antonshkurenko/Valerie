package me.cullycross.valerie.graphics.renderers

import android.content.Context
import android.opengl.GLES20
import graphics.utils.VertexArray
import me.cullycross.valerie.graphics.objects.ViewObjectBuilder
import me.cullycross.valerie.graphics.programs.SingleColorProgram
import me.cullycross.valerie.graphics.utils.POSITION_COMPONENT_COUNT
import me.cullycross.valerie.utils.Point
import me.cullycross.valerie.utils.Vector
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/10/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

// todo(tonyshkurenko), 1/17/16:  make proxy for basic circle and line etc
class ShuffleRenderer(context: Context) : Abstract2dRenderer(context) {

    private var program: SingleColorProgram? = null

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        super.onSurfaceCreated(gl, config)
        program = SingleColorProgram(context)
    }

    override fun onDrawFrame(gl: GL10) {
        super.onDrawFrame(gl)

        program?.useProgram()

        var lineData = ViewObjectBuilder.createLine(Point(), 0.1f, Point(0.4f, 0.2f), 0.07f, aspectRatio)
        var lineDrawables = lineData.drawableList
        var lineVertexArray = VertexArray(lineData.vertexData)

        positionObjectInScene(0f, 0f)
        program?.setUniforms(modelProjectionMatrix, g = 1f)
        lineVertexArray.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                POSITION_COMPONENT_COUNT, 0);
        lineDrawables.forEach {
            it.draw()
        }

        val circleData = ViewObjectBuilder.createCircle(Point(), 0.02f, 32, aspectRatio)
        val circleDrawables = circleData.drawableList
        val circleVertexArray = VertexArray(circleData.vertexData)

        positionObjectInScene(0f, 0f)
        program?.setUniforms(modelProjectionMatrix, 1f)
        circleVertexArray.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                POSITION_COMPONENT_COUNT, 0);
        circleDrawables.forEach {
            it.draw()
        }

        positionObjectInScene(0.4f, 0.2f)
        program?.setUniforms(modelProjectionMatrix, 1f)
        circleVertexArray.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                POSITION_COMPONENT_COUNT, 0);
        circleDrawables.forEach {
            it.draw()
        }
    }
}