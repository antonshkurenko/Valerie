package me.cullycross.valerie.graphics.objects


import android.opengl.GLES20.*
import graphics.utils.VertexArray
import me.cullycross.valerie.objects.Drawable
import me.cullycross.valerie.utils.Circle
import me.cullycross.valerie.utils.Point
import me.cullycross.valerie.utils.Vector
import java.util.*

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

const val FLOATS_PER_VERTEX = 2 // X, Y

class ViewObjectBuilder public constructor(sizeInVertices: Int) {

    private val vertexData: FloatArray
    private val drawList = ArrayList<Drawable>()
    private var offset = 0

    init {
        vertexData = FloatArray(sizeInVertices * FLOATS_PER_VERTEX)
    }

    /**
     * appends circle to the figure
     */
    private fun appendCircle(circle: Circle, numPoints: Int, aspectRatio: Float):
            ViewObjectBuilder {

        val startVertex = offset / FLOATS_PER_VERTEX
        val numVertices = sizeOfCircleInVertices(numPoints)

        vertexData[offset++] = circle.center.x / aspectRatio
        vertexData[offset++] = circle.center.y

        for (i in 0..numPoints) {
            val angleInRadians = (i.toFloat() / numPoints.toFloat()) * (Math.PI.toFloat() * 2f)

            val c = Math.cos(angleInRadians.toDouble()).toFloat()
            val s = Math.sin(angleInRadians.toDouble()).toFloat()

            vertexData[offset++] = circle.center.x + circle.radius * c / aspectRatio
            vertexData[offset++] = circle.center.y + circle.radius * s
        }

        drawList.add(object : Drawable {
            override fun draw() = glDrawArrays(GL_TRIANGLE_FAN, startVertex, numVertices)
        })
        return this
    }

    private fun appendLine(from: Point, startWidth: Float, to: Point, endWidth: Float, aspectRatio: Float):
            ViewObjectBuilder {

        val vector = Vector(from, to)

        val angle = vector.perpendicularCCW().angle()
        val halfStartLength = startWidth / 2f
        val halfEndLength = endWidth / 2f


        val factor = 1f / aspectRatio
        val a = from.translate(Vector.fromLengthAndAngle(halfStartLength, angle).scaleX(factor).scaleY(aspectRatio))
        val b = from.translate(Vector.fromLengthAndAngle(-halfStartLength, angle).scaleX(factor).scaleY(aspectRatio))
        val c = to.translate(Vector.fromLengthAndAngle(halfEndLength, angle).scaleX(factor).scaleY(aspectRatio))
        val d = to.translate(Vector.fromLengthAndAngle(-halfEndLength, angle).scaleX(factor).scaleY(aspectRatio))

        val startVertex = offset / FLOATS_PER_VERTEX

        /**
         *
         *        A
         *       / \
         *      /      \
         *     . p1        \
         *    /                \D
         *   /                  .p2
         *  B --------------- C
         *
         */
        // Triangle strip BADC
        // B
        vertexData[offset++] = b.x
        vertexData[offset++] = b.y
        // A
        vertexData[offset++] = a.x
        vertexData[offset++] = a.y
        // D
        vertexData[offset++] = d.x
        vertexData[offset++] = d.y
        // C
        vertexData[offset++] = c.x
        vertexData[offset++] = c.y

        drawList.add(object : Drawable {
            override fun draw() = glDrawArrays(GL_TRIANGLE_STRIP, startVertex, 4)
        })
        return this
    }

    private fun build(): GeneratedData {
        return GeneratedData(VertexArray(vertexData), drawList)
    }

    companion object {

        fun createCircle(center: Point, radius: Float, numPoints: Int,
                         aspectRatio: Float): GeneratedData {

            val size = sizeOfCircleInVertices(numPoints)

            val circle = Circle(center, radius)

            return ViewObjectBuilder(size).appendCircle(circle, numPoints, aspectRatio).build()
        }

        fun createLine(from: Point = Point(),
                       length: Float = 0.1f,
                       angle: Float,
                       startWidth: Float = 0.02f,
                       endWidth: Float = startWidth,
                       aspectRatio: Float): GeneratedData {
            return ViewObjectBuilder(4).appendLine(
                    from, startWidth, from.translate(Vector.fromLengthAndAngle(length, angle)), endWidth, aspectRatio).build()
        }

        fun createLine(from: Point = Point(),
                       startWidth: Float = 0.02f,
                       to: Point,
                       endWidth: Float = startWidth,
                       aspectRatio: Float): GeneratedData {
            return ViewObjectBuilder(4).appendLine(from, startWidth, to, endWidth, aspectRatio).build()
        }

        fun sizeOfCircleInVertices(numPoints: Int): Int {
            return 1 + (numPoints + 1)
        }
    }

    data class GeneratedData(val vertexData: VertexArray, val drawableList: List<Drawable>)
}
