package graphics.utils

import android.opengl.GLES20.*
import timber.log.Timber

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
fun compileVertexShader(shaderCode: String): Int {
    return compileShader(GL_VERTEX_SHADER, shaderCode)
}

fun compileFragmentShader(shaderCode: String): Int {
    return compileShader(GL_FRAGMENT_SHADER, shaderCode)
}

fun linkProgram(vertexShaderId: Int, fragmentShaderId: Int): Int {
    val programObjectId = glCreateProgram()

    if (programObjectId == 0) {
        Timber.w("Could not create new program")

        return 0
    }

    glAttachShader(programObjectId, vertexShaderId)
    glAttachShader(programObjectId, fragmentShaderId)

    glLinkProgram(programObjectId)

    val linkStatus = IntArray(1)
    glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0)

    if (linkStatus[0] == 0) {

        glDeleteProgram(programObjectId)

        return 0
    }

    return programObjectId
}

fun validateProgram(programObjectId: Int): Boolean {
    glValidateProgram(programObjectId)

    val validateStatus = IntArray(1)
    glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0)

    return validateStatus[0] != 0
}

fun buildProgram(vertexShaderSource: String, fragmentShaderSource: String): Int {
    val program: Int

    val vertexShader = compileVertexShader(vertexShaderSource)
    val fragmentShader = compileFragmentShader(fragmentShaderSource)

    program = linkProgram(vertexShader, fragmentShader)

    validateProgram(program)

    return program
}

private fun compileShader(type: Int, shaderCode: String): Int {
    val shaderObjectId = glCreateShader(type)

    if (shaderObjectId == 0) {
        Timber.w("Could not create new shader")
        return 0
    }
    glShaderSource(shaderObjectId, shaderCode)

    glCompileShader(shaderObjectId)

    val compileStatus = IntArray(1)
    glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0)

    Timber.v("Results of compiling source:" + "\n" + shaderCode + "\n:" + glGetShaderInfoLog(
            shaderObjectId))

    if (compileStatus[0] == 0) {
        glDeleteShader(shaderObjectId)

        Timber.w("Compilation of shader failed.")

        return 0
    }

    return shaderObjectId
}
