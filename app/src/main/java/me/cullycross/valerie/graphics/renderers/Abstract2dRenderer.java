package me.cullycross.valerie.graphics.renderers;

import android.content.Context;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import timber.log.Timber;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.orthoM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public abstract class Abstract2dRenderer implements GLSurfaceView.Renderer {

  // @formatter:off
    protected final float[] mProjectionMatrix = new float[16];
   protected final float[  ] mModelMatrix = new float[16];
  protected final float[    ] mModelProjectionMatrix = new float[16];
  // @formatter:on

  protected final Context mContext;

  protected float mAspectRatio;

  public Abstract2dRenderer(Context context) {
    mContext = context;
  }

  @Override public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
  }

  @Override public void onSurfaceChanged(GL10 gl, int width, int height) {
    glViewport(0, 0, width, height);

    // currently it's width / height
    mAspectRatio = (float) width / (float) height;

    Timber.i("Width is %d, height is %d, aspect is %f", width, height, mAspectRatio);

    // use aspect ratio not here, but later
    orthoM(mProjectionMatrix, 0, -1f, 1f, -1f, 1f, -1f, 1f);
  }

  @Override public void onDrawFrame(GL10 gl) {
    glClear(GL_COLOR_BUFFER_BIT);
  }

  protected void positionObjectInScene(float x, float y) {
    setIdentityM(mModelMatrix, 0);
    translateM(mModelMatrix, 0, x, y, 0f);
    multiplyMM(mModelProjectionMatrix, 0, mProjectionMatrix, 0, mModelMatrix, 0);
  }
}
