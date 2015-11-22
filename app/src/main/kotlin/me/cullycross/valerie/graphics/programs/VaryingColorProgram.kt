package graphics.programs

import android.content.Context
import android.opengl.GLES20.*
import me.cullycross.valerie.R
import graphics.programs.AbstractShaderProgram

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class VaryingColorProgram(context: Context) :
        AbstractShaderProgram(
                context,
                R.raw.varying_color_vertex_shader,
                R.raw.varying_color_fragment_shader
        ) {

    //Uniforms
    private val uMatrixLocation: Int

    //Attributes
    val positionLocation: Int
    val colorLocation: Int

    init {

        uMatrixLocation = glGetUniformLocation(mProgram, U_MATRIX)

        positionLocation = glGetAttribLocation(mProgram, A_POSITION)
        colorLocation = glGetAttribLocation(mProgram, A_COLOR)
    }

    fun setUniforms(projectionMatrix: FloatArray) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0)
    }
}
