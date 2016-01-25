package me.cullycross.valerie.graphics.renderers

import android.content.Context
import graphics.utils.VertexArray
import me.cullycross.valerie.graphics.objects.ViewObjectBuilder
import me.cullycross.valerie.graphics.programs.SingleColorProgram
import me.cullycross.valerie.graphics.utils.POSITION_COMPONENT_COUNT
import me.cullycross.valerie.objects.Drawable
import me.cullycross.valerie.objects.Line
import me.cullycross.valerie.objects.directors.Director
import me.cullycross.valerie.objects.directors.HorizontalLineDirector
import me.cullycross.valerie.utils.Point
import java.util.*
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

        val director: Director<Line> = HorizontalLineDirector(0.04f)

        val lineList: MutableList<Line> = ArrayList()

        val lineData = ViewObjectBuilder.createLine(Point(), 0.02f, Point(0.1f, 0.1f), 0.01f, aspectRatio)
        val lineDrawables = lineData.drawableList
        val lineVertexArray = VertexArray(lineData.vertexData)

        for (i in 0..10) {
            val tempLine = Line()
            tempLine.image = object : Drawable {
                override fun draw() {
                    positionObjectInScene(tempLine.position.x, tempLine.position.y)
                    program?.setUniforms(modelProjectionMatrix, g = 1f)
                    lineVertexArray.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                            POSITION_COMPONENT_COUNT, 0);
                    lineDrawables.forEach {
                        it.draw()
                    }
                }
            }
            lineList.add(tempLine)
        }

        director.direct(Point(), lineList)

        for(line in lineList) {
            line.draw()
        }

        /*var lineData = ViewObjectBuilder.createLine(Point(), 0.1f, Point(0.4f, 0.2f), 0.07f, aspectRatio)
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
        }*/
    }
}