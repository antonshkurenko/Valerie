package graphics.programs


import android.content.Context
import graphics.programs.AbstractShaderProgram
import me.cullycross.valerie.R

import android.opengl.GLES20.glGetAttribLocation
import android.opengl.GLES20.glGetUniformLocation
import android.opengl.GLES20.glUniform4f
import android.opengl.GLES20.glUniformMatrix4fv

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class SingleColorProgram(context: Context) :
        AbstractShaderProgram(
                context,
                R.raw.varying_color_vertex_shader,
                R.raw.single_color_fragment_shader
        ) {

    // Uniforms
    private val uColorLocation: Int
    private val uMatrixLocation: Int

    // Attributes
    val positionLocation: Int

    init {

        uColorLocation = glGetUniformLocation(mProgram, U_COLOR)
        uMatrixLocation = glGetUniformLocation(mProgram, U_MATRIX)

        positionLocation = glGetAttribLocation(mProgram, A_POSITION)
    }

    fun setUniforms(matrix: FloatArray, r: Float, g: Float, b: Float) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0)
        glUniform4f(uColorLocation, r, g, b, 1f)
    }
}