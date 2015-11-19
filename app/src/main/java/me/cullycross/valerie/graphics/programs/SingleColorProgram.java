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
public class SingleColorProgram extends AbstractShaderProgram {

  // Uniforms
  private int uColorLocation;
  private int uMatrixLocation;

  // Attributes
  private int aPositionLocation;

  public SingleColorProgram(Context context) {
    super(context, R.raw.varying_color_vertex_shader, R.raw.single_color_fragment_shader);

    uColorLocation = glGetUniformLocation(mProgram, U_COLOR);
    uMatrixLocation = glGetUniformLocation(mProgram, U_MATRIX);

    aPositionLocation = glGetAttribLocation(mProgram, A_POSITION);
  }

  public void setUniforms(float[] matrix, float r, float g, float b) {
    glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    glUniform4f(uColorLocation, r, g, b, 1f);
  }

  public int getPositionLocation() {
    return aPositionLocation;
  }
}