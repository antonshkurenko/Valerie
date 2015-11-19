package me.cullycross.valerie.graphics.programs;

import android.content.Context;
import me.cullycross.valerie.R;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class VaryingColorProgram extends AbstractShaderProgram {

  //Uniforms
  private final int uMatrixLocation;

  //Attributes
  private final int aPositionLocation;
  private final int aColorLocation;

  public VaryingColorProgram(Context context) {
    super(context, R.raw.varying_color_vertex_shader, R.raw.varying_color_fragment_shader);

    uMatrixLocation = glGetUniformLocation(mProgram, U_MATRIX);

    aPositionLocation = glGetAttribLocation(mProgram, A_POSITION);
    aColorLocation = glGetAttribLocation(mProgram, A_COLOR);
  }

  public void setUniforms(float [] projectionMatrix) {
    glUniformMatrix4fv(uMatrixLocation, 1, false, projectionMatrix, 0);
  }

  public int getPositionLocation() {
    return aPositionLocation;
  }

  public int getColorLocation() {
    return aColorLocation;
  }
}
