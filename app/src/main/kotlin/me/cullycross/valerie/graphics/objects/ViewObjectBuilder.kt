package graphics.objects


import android.opengl.GLES20.GL_TRIANGLE_FAN
import android.opengl.GLES20.glDrawArrays
import objects.Drawable
import utils.Circle
import utils.Point
import java.util.*

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class ViewObjectBuilder private constructor(sizeInVertices: Int) {

    private val mVertexData: FloatArray
    private val drawList = ArrayList<() -> Unit>()
    private var mOffset = 0

    init {
        mVertexData = FloatArray(sizeInVertices * FLOATS_PER_VERTEX)
    }

    private fun appendCircle(circle: Circle, numPoints: Int, aspectRatio: Float): ViewObjectBuilder {

        val startVertex = mOffset / FLOATS_PER_VERTEX
        val numVertices = sizeOfCircleInVertices(numPoints)

        mVertexData[mOffset++] = circle.center.x / aspectRatio
        mVertexData[mOffset++] = circle.center.y

        for (i in 0..numPoints) {
            val angleInRadians = (i.toFloat() / numPoints.toFloat()) * (Math.PI.toFloat() * 2f)

            val c = Math.cos(angleInRadians.toDouble()).toFloat()
            val s = Math.sin(angleInRadians.toDouble()).toFloat()

            mVertexData[mOffset++] = circle.center.x + circle.radius * c / aspectRatio

            mVertexData[mOffset++] = circle.center.y + circle.radius * s

        }

        drawList.add { glDrawArrays(GL_TRIANGLE_FAN, startVertex, numVertices) }
        return this
    }

    private fun build(): GeneratedData {
        return GeneratedData(mVertexData, drawList)
    }

    data class GeneratedData(val mVertexData: FloatArray, val mDrawableList: List<() -> Unit>)

    companion object {

        private val FLOATS_PER_VERTEX = 2 // X, Y

        internal fun createCircle(center: Point, radius: Float, numPoints: Int,
                                  aspectRatio: Float): GeneratedData {

            val size = sizeOfCircleInVertices(numPoints)

            val circle = Circle(center, radius)

            return ViewObjectBuilder(size).appendCircle(circle, numPoints, aspectRatio).build()
        }

        private fun sizeOfCircleInVertices(numPoints: Int): Int {
            return 1 + (numPoints + 1)
        }
    }
}
