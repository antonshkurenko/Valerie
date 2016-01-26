package me.cullycross.valerie.graphics.utils

import android.opengl.Matrix

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/26/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

fun positionObjectInScene(projectionMatrix: FloatArray,
                          x: Float,
                          y: Float): FloatArray {

    val modelMatrix = FloatArray(16)
    val modelProjectionMatrix = FloatArray(16)

    Matrix.setIdentityM(modelMatrix, 0)
    Matrix.translateM(modelMatrix, 0, x, y, 0f)
    Matrix.multiplyMM(modelProjectionMatrix, 0, projectionMatrix, 0, modelMatrix, 0)
    return modelProjectionMatrix
}

fun positionObjectInScene(projectionMatrix: FloatArray,
                          x: Float,
                          y: Float,
                          angle: Float): FloatArray {

    val modelMatrix = FloatArray(16)
    val modelProjectionMatrix = FloatArray(16)

    Matrix.setIdentityM(modelMatrix, 0)
    Matrix.translateM(modelMatrix, 0, x, y, 0f)
    Matrix.rotateM(modelMatrix, 0, Math.toDegrees(angle.toDouble()).toFloat() - 90f, 0f, 0f, 1f)
    Matrix.multiplyMM(modelProjectionMatrix, 0, projectionMatrix, 0, modelMatrix, 0)
    return modelProjectionMatrix
}