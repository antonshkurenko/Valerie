package me.cullycross.valerie.graphics.programs;

import android.content.Context;
import me.cullycross.valerie.graphics.utils.ShaderUtils;
import me.cullycross.valerie.utils.TextResourseUtils;

import static android.opengl.GLES20.glUseProgram;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public abstract class AbstractShaderProgram {

  //Uniforms
  protected static final String U_MATRIX = "u_Matrix";
  protected static final String U_COLOR = "u_Color";
  protected static final String U_TEXTURE_UNIT = "u_TextureUnit";

  //Attributes
  protected static final String A_POSITION = "a_Position";
  protected static final String A_COLOR = "a_Color";
  protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

  //Shader
  protected final int mProgram;

  protected AbstractShaderProgram(Context context, int vertexShaderResourceId,
      int fragmentShaderResourceId) {

    mProgram = ShaderUtils.buildProgram(
        TextResourseUtils.readTextFileFromResource(context, vertexShaderResourceId),
        TextResourseUtils.readTextFileFromResource(context, fragmentShaderResourceId));
  }

  public void useProgram() {
    glUseProgram(mProgram);
  }
}
