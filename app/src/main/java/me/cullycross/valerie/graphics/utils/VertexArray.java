package me.cullycross.valerie.graphics.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class VertexArray {

  private final FloatBuffer mFloatBuffer;

  public VertexArray(float[] vertexData) {
    mFloatBuffer = ByteBuffer.allocateDirect(vertexData.length * Constants.BYTES_PER_FLOAT)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer()
        .put(vertexData);
  }

  public void setVertexAttribPointer(int dataOffset, int attributeLocation, int componentCount,
      int stride) {
    mFloatBuffer.position(dataOffset);
    glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT, false, stride, mFloatBuffer);
    glEnableVertexAttribArray(attributeLocation);
    mFloatBuffer.position(0);
  }

  public void updateBuffer(float[] vertexData, int start, int count) {
    mFloatBuffer.position(start);
    mFloatBuffer.put(vertexData, start, count);
    mFloatBuffer.position(0);
  }
}
