package me.cullycross.valerie.graphics.renderers

import android.content.Context
import me.cullycross.valerie.graphics.objects.factories.DefaultViewFactory
import me.cullycross.valerie.graphics.objects.factories.ViewFactory
import me.cullycross.valerie.graphics.programs.SingleColorProgram
import me.cullycross.valerie.graphics.utils.POSITION_COMPONENT_COUNT
import me.cullycross.valerie.graphics.utils.positionObjectInScene
import me.cullycross.valerie.objects.BaseObject
import me.cullycross.valerie.objects.BaseRotatableObject
import me.cullycross.valerie.objects.Drawable
import me.cullycross.valerie.objects.directors.Director
import me.cullycross.valerie.objects.directors.HorizontalLineDirector
import me.cullycross.valerie.objects.directors.modifiers.SunModifier
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

class ShuffleRenderer(context: Context) : Abstract2dRenderer(context) {

    private var program: SingleColorProgram? = null
    private var factory: ViewFactory? = null

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        super.onSurfaceCreated(gl, config)
        program = SingleColorProgram(context)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        factory = DefaultViewFactory(aspectRatio)
    }

    override fun onDrawFrame(gl: GL10) {
        super.onDrawFrame(gl)

        program?.useProgram()

        // ******************* Lines ********************

        val rotatableDirector: Director<BaseRotatableObject> = HorizontalLineDirector(0.04f)

        val lineList: MutableList<BaseRotatableObject> = ArrayList()

        for (i in 0..20) {

            val line = BaseRotatableObject()

            line.image = object : Drawable {
                override fun draw() {
                    program?.setUniforms(
                            positionObjectInScene(
                                    line.position.x,
                                    line.position.y,
                                    line.angle), g = 1f)
                    factory?.getLine()?.vertexData?.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                            POSITION_COMPONENT_COUNT, 0);
                    factory?.getLine()?.drawableList?.forEach { it.draw() }
                }
            }

            lineList.add(line)
        }

        rotatableDirector.direct(Point(), lineList, SunModifier().modify())

        lineList.forEach { it.draw() }

        // ******************* Circles ********************

        val baseDirector: Director<BaseObject> = HorizontalLineDirector(0.075f)

        val circleList: MutableList<BaseObject> = ArrayList()

        for (i in 0..8) {

            val circle = BaseObject()

            circle.image = object : Drawable {
                override fun draw() {
                    program?.setUniforms(
                            positionObjectInScene(
                                    circle.position.x,
                                    circle.position.y), r = 1f)
                    factory?.getCircle()?.vertexData?.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                            POSITION_COMPONENT_COUNT, 0);
                    factory?.getCircle()?.drawableList?.forEach { it.draw() }
                }
            }

            circleList.add(circle)
        }

        baseDirector.direct(Point(y = -0.25f), circleList)

        circleList.forEach { it.draw() }
    }
}