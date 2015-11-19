package me.cullycross.valerie.graphics.objects;

import java.util.ArrayList;
import java.util.List;
import me.cullycross.valerie.objects.Drawable;
import me.cullycross.valerie.utils.GeometryUtils;


import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class ViewObjectBuilder {

  private static final int FLOATS_PER_VERTEX = 2; // X, Y

  private final float[] mVertexData;
  private final List<Drawable> mDrawList = new ArrayList<>();
  private int mOffset = 0;

  static GeneratedData createCircle(GeometryUtils.Point center, float radius, int numPoints,
      float aspectRatio) {

    int size = sizeOfCircleInVertices(numPoints);

    GeometryUtils.Circle circle = new GeometryUtils.Circle(center, radius);

    return new ViewObjectBuilder(size).appendCircle(circle, numPoints, aspectRatio).build();
  }

  private static int sizeOfCircleInVertices(int numPoints) {
    return 1 + (numPoints + 1);
  }

  private ViewObjectBuilder(int sizeInVertices) {
    mVertexData = new float[sizeInVertices * FLOATS_PER_VERTEX];
  }

  private ViewObjectBuilder appendCircle(GeometryUtils.Circle circle, int numPoints, float aspectRatio) {

    final int startVertex = mOffset / FLOATS_PER_VERTEX;
    final int numVertices = sizeOfCircleInVertices(numPoints);

    mVertexData[mOffset++] = circle.center.x / aspectRatio;
    mVertexData[mOffset++] = circle.center.y;

    for (int i = 0; i <= numPoints; i++) {
      float angleInRadians = ((float) i / (float) numPoints) * ((float) Math.PI * 2f);

      final float c = (float) Math.cos(angleInRadians);
      final float s = (float) Math.sin(angleInRadians);

      mVertexData[mOffset++] =
          circle.center.x + circle.radius * c / aspectRatio;

      mVertexData[mOffset++] = circle.center.y + circle.radius * s;

    }

    mDrawList.add(() -> glDrawArrays(GL_TRIANGLE_FAN, startVertex, numVertices));
    return this;
  }

  private GeneratedData build() {
    return new GeneratedData(mVertexData, mDrawList);
  }

  static class GeneratedData {
    final float[] mVertexData;
    final List<Drawable> mDrawableList;

    GeneratedData(float[] vertexData, List<Drawable> drawableList) {
      this.mVertexData = vertexData;
      this.mDrawableList = drawableList;
    }
  }
}
