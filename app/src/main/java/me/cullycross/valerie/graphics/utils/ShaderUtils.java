package me.cullycross.valerie.graphics.utils;

import timber.log.Timber;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class ShaderUtils {
  public static int compileVertexShader(String shaderCode) {
    return compileShader(GL_VERTEX_SHADER, shaderCode);
  }

  public static int compileFragmentShader(String shaderCode) {
    return compileShader(GL_FRAGMENT_SHADER, shaderCode);
  }

  private static int compileShader(int type, String shaderCode) {
    final int shaderObjectId = glCreateShader(type);

    if (shaderObjectId == 0) {
      Timber.w("Could not create new shader");
      return 0;
    }
    glShaderSource(shaderObjectId, shaderCode);

    glCompileShader(shaderObjectId);

    final int[] compileStatus = new int[1];
    glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);

    /*Timber.v("Results of compiling source:" + "\n" + shaderCode + "\n:" + glGetShaderInfoLog(
        shaderObjectId));*/

    if (compileStatus[0] == 0) {
      glDeleteShader(shaderObjectId);

      Timber.w("Compilation of shader failed.");

      return 0;
    }

    return shaderObjectId;
  }

  public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
    final int programObjectId = glCreateProgram();

    if (programObjectId == 0) {
      Timber.w("Could not create new program");

      return 0;
    }

    glAttachShader(programObjectId, vertexShaderId);
    glAttachShader(programObjectId, fragmentShaderId);

    glLinkProgram(programObjectId);

    final int[] linkStatus = new int[1];
    glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);

    if (linkStatus[0] == 0) {

      glDeleteProgram(programObjectId);

      return 0;
    }

    return programObjectId;
  }

  public static boolean validateProgram(int programObjectId) {
    glValidateProgram(programObjectId);

    final int[] validateStatus = new int[1];
    glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0);

    return validateStatus[0] != 0;
  }

  public static int buildProgram(String vertexShaderSource, String fragmentShaderSource) {
    int program;

    int vertexShader = compileVertexShader(vertexShaderSource);
    int fragmentShader = compileFragmentShader(fragmentShaderSource);

    program = linkProgram(vertexShader, fragmentShader);

    validateProgram(program);

    return program;
  }
}
