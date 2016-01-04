package me.cullycross.valerie.graphics.renderers

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import android.opengl.Matrix.*
import timber.log.Timber
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
abstract class Abstract2dRenderer(protected val context: Context) : GLSurfaceView.Renderer {

    protected val projectionMatrix = FloatArray(16)
    protected val modelMatrix = FloatArray(16)
    protected val modelProjectionMatrix = FloatArray(16)

    protected var aspectRatio: Float = 0f

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        glViewport(0, 0, width, height)

        // currently it's width / height
        aspectRatio = width.toFloat() / height.toFloat()

        Timber.i("Width is %d, height is %d, aspect is %f", width, height, aspectRatio)

        // use aspect ratio not here, but later
        orthoM(projectionMatrix, 0, -1f, 1f, -1f, 1f, -1f, 1f)
    }

    override fun onDrawFrame(gl: GL10) {
        glClear(GL_COLOR_BUFFER_BIT)
    }

    protected fun positionObjectInScene(x: Float, y: Float) {
        setIdentityM(modelMatrix, 0)
        translateM(modelMatrix, 0, x, y, 0f)
        multiplyMM(modelProjectionMatrix, 0, projectionMatrix, 0, modelMatrix, 0)
    }
}
