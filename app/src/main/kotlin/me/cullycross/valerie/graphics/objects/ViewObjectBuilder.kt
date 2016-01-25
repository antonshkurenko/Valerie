package me.cullycross.valerie.graphics.objects


import android.opengl.GLES20.*
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

    // fixme(tonyshkurenko), 1/17/16:  fix this, cause it doesn't work
    private fun appendLine(from: Point, startWidth: Float, to: Point, endWidth: Float):
            ViewObjectBuilder {
        val vector = Vector(from, to)

        val angle = vector.perpendicularCCW().angle()
        val halfStartLength = startWidth / 2f
        val halfEndLength = endWidth / 2f

        val a = from.translate(Vector.fromLengthAndAngle(halfStartLength, angle))
        val b = from.translate(Vector.fromLengthAndAngle(-halfStartLength, angle))
        val c = to.translate(Vector.fromLengthAndAngle(halfEndLength, angle))
        val d = to.translate(Vector.fromLengthAndAngle(-halfEndLength, angle))

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
        // Triangle strip ABDC
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
        vertexData[offset++] = c.y
        vertexData[offset++] = c.y

        drawList.add(object : Drawable {
            override fun draw() = glDrawArrays(GL_TRIANGLE_STRIP, startVertex, 4)
        })
        return this
    }

    private fun build(): GeneratedData {
        return GeneratedData(vertexData, drawList)
    }

    data class GeneratedData(val vertexData: FloatArray, val drawableList: List<Drawable>)

    companion object {

        fun createCircle(center: Point, radius: Float, numPoints: Int,
                         aspectRatio: Float): GeneratedData {

            val size = sizeOfCircleInVertices(numPoints)

            val circle = Circle(center, radius)

            return ViewObjectBuilder(size).appendCircle(circle, numPoints, aspectRatio).build()
        }

        fun createLine(from: Point, length: Float, angle: Float, width: Float): GeneratedData {
            return ViewObjectBuilder(4).appendLine(
                    from, width, from.translate(Vector(length, angle)), width).build()
        }

        fun createLine(from: Point, startWidth: Float, to: Point, endWidth: Float): GeneratedData {
            return ViewObjectBuilder(4).appendLine(from, startWidth, to, endWidth).build()
        }

        fun sizeOfCircleInVertices(numPoints: Int): Int {
            return 1 + (numPoints + 1)
        }
    }
}
