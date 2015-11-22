package graphics.programs

import android.content.Context
import android.opengl.GLES20.glUseProgram
import graphics.utils.buildProgram
import utils.readTextFileFromResource

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
abstract class AbstractShaderProgram
protected constructor(context: Context, vertexShaderResourceId: Int,
                      fragmentShaderResourceId: Int) {

    //Shader
    protected val mProgram: Int

    init {

        mProgram = buildProgram(
                readTextFileFromResource(context, vertexShaderResourceId),
                readTextFileFromResource(context, fragmentShaderResourceId))
    }

    fun useProgram() {
        glUseProgram(mProgram)
    }
}
