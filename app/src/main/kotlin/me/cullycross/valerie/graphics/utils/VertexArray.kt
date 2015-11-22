package graphics.utils

import android.opengl.GLES20.*
import graphics.utils.BYTES_PER_FLOAT
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class VertexArray(vertexData: FloatArray) {

    private val floatBuffer: FloatBuffer

    init {
        floatBuffer =
                ByteBuffer.allocateDirect(
                        vertexData.size * BYTES_PER_FLOAT)
                        .order(ByteOrder.nativeOrder())
                        .asFloatBuffer()
                        .put(vertexData)
    }

    fun setVertexAttribPointer(dataOffset: Int, attributeLocation: Int, componentCount: Int,
                               stride: Int) {
        floatBuffer.position(dataOffset)
        glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT, false, stride, floatBuffer)
        glEnableVertexAttribArray(attributeLocation)
        floatBuffer.position(0)
    }

    fun updateBuffer(vertexData: FloatArray, start: Int, count: Int) {
        floatBuffer.position(start)
        floatBuffer.put(vertexData, start, count)
        floatBuffer.position(0)
    }
}
