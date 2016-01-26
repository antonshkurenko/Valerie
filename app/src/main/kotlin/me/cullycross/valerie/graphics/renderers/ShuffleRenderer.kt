package me.cullycross.valerie.graphics.renderers

import android.content.Context
import graphics.utils.VertexArray
import me.cullycross.valerie.graphics.objects.DefaultViewFactory
import me.cullycross.valerie.graphics.objects.ViewFactory
import me.cullycross.valerie.graphics.objects.ViewObjectBuilder
import me.cullycross.valerie.graphics.programs.SingleColorProgram
import me.cullycross.valerie.graphics.utils.POSITION_COMPONENT_COUNT
import me.cullycross.valerie.graphics.utils.positionObjectInScene
import me.cullycross.valerie.objects.Drawable
import me.cullycross.valerie.objects.Line
import me.cullycross.valerie.objects.directors.Director
import me.cullycross.valerie.objects.directors.HorizontalLineDirector
import me.cullycross.valerie.utils.Point
import me.cullycross.valerie.utils.Vector
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

        val viewFactory: ViewFactory = DefaultViewFactory(aspectRatio)

        for (i in 0..20) {

            val line = Line()

            line.image = object : Drawable {
                override fun draw() {
                    program?.setUniforms(
                            positionObjectInScene(
                                    projectionMatrix,
                                    line.position.x,
                                    line.position.y,
                                    line.angle), g = 1f)
                    viewFactory.createLine().vertexData.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                            POSITION_COMPONENT_COUNT, 0);
                    viewFactory.createLine().drawableList.forEach { it.draw() }
                }
            }

            lineList.add(line)
        }

        director.direct(Point(), lineList, HorizontalLineDirector.sunModifier(Point(y = -0.5f)))

        for (line in lineList) {
            line.draw()
        }

        program?.setUniforms(positionObjectInScene(projectionMatrix, 0f, -0.5f), 1f)
        viewFactory.createCircle().vertexData.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                POSITION_COMPONENT_COUNT, 0);
        viewFactory.createCircle().drawableList.forEach {
            it.draw()
        }
    }
}