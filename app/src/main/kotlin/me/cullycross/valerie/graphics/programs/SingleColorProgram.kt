package me.cullycross.valerie.graphics.programs


import android.content.Context
import android.opengl.GLES20.*
import graphics.programs.A_POSITION
import graphics.programs.AbstractShaderProgram
import graphics.programs.U_COLOR
import graphics.programs.U_MATRIX
import me.cullycross.valerie.R

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

    /**
     * @param r
     * @param g
     * @param b from 0f to 1f (0..255)
     */
    fun setUniforms(matrix: FloatArray, r: Float = 0f, g: Float = 0f, b: Float = 0f) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0)
        glUniform4f(uColorLocation, r, g, b, 1f)
    }
}