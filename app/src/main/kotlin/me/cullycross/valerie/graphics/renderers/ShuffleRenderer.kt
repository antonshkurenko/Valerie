package me.cullycross.valerie.graphics.renderers

import android.content.Context
import me.cullycross.valerie.graphics.objects.factories.DefaultViewFactory
import me.cullycross.valerie.graphics.objects.factories.ViewFactory
import me.cullycross.valerie.graphics.programs.SingleColorProgram
import me.cullycross.valerie.graphics.utils.POSITION_COMPONENT_COUNT
import me.cullycross.valerie.mechanics.Machinarium
import me.cullycross.valerie.mechanics.directors.Director
import me.cullycross.valerie.mechanics.directors.HorizontalLineDirector
import me.cullycross.valerie.mechanics.objects.BaseObject
import me.cullycross.valerie.mechanics.objects.BaseRotatableObject
import me.cullycross.valerie.mechanics.objects.Drawable
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

class ShuffleRenderer(context: Context, private val mechs: Machinarium) : Abstract2dRenderer(context) {

    private var program: SingleColorProgram? = null
    private var factory: ViewFactory? = null

    private val rotatableDirector: Director<BaseRotatableObject> = HorizontalLineDirector(0.04f)
    private val baseDirector: Director<BaseObject> = HorizontalLineDirector(0.075f)

    val lineList: MutableList<BaseRotatableObject> = ArrayList()
    val circleList: MutableList<BaseObject> = ArrayList()

    init {
        mechs.objects.forEach {
            it.image = object : Drawable {
                override fun draw(obj: BaseObject) {
                    program?.setUniforms(obj.matrix, g = 1f)
                    factory?.getLine()?.vertexData?.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                            POSITION_COMPONENT_COUNT, 0);
                    factory?.getLine()?.drawableList?.forEach { it.invoke() }
                }
            }
        }

        /*// lines
        for (i in 0..20) {
            lineList.add(BaseRotatableObject(image = object : Drawable {
                override fun draw(obj: BaseObject) {
                    program?.setUniforms(obj.matrix, g = 1f)
                    factory?.getLine()?.vertexData?.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                            POSITION_COMPONENT_COUNT, 0);
                    factory?.getLine()?.drawableList?.forEach { it.invoke() }
                }
            }))
        }

        // circles
        for (i in 0..8) {
            circleList.add(BaseObject(image = object : Drawable {
                override fun draw(obj: BaseObject) {
                    program?.setUniforms(obj.matrix, r = 1f)
                    factory?.getCircle()?.vertexData?.setVertexAttribPointer(0, program?.positionLocation ?: 0,
                            POSITION_COMPONENT_COUNT, 0);
                    factory?.getCircle()?.drawableList?.forEach { it.invoke() }
                }
            }))
        }*/
    }

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

        // todo(tonyshkurenko), 1/30/16: fix concurrent modification exception, do smth with this!
        mechs.objects.forEach {
            it.draw()
        }

        /*rotatableDirector.direct(Point(), lineList, SunModifier().modify())
        lineList.forEach { it.draw() }

        baseDirector.direct(Point(y = -0.25f), circleList)
        circleList.forEach { it.draw() }*/
    }
}