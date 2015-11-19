package me.cullycross.valerie.utils;

import java.util.Random;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class GeometryUtils {

  public static boolean intersects(Sphere sphere, Ray ray) {
    return distanceBetween(sphere.center, ray) < sphere.radius;
  }

  private static float distanceBetween(Point center, Ray ray) {
    Vector p1ToPoint = new Vector(ray.point, center);
    Vector p2ToPoint = new Vector(ray.point.translate(ray.vector), center);

    float areaOfTriangleTimesTwo = p1ToPoint.crossProduct(p2ToPoint).length();
    float lengthOfBase = ray.vector.length();

    return areaOfTriangleTimesTwo / lengthOfBase;
  }

  public static Point intersectionPoint(Ray ray, Plane plane) {
    Vector rayToPlaneVector = new Vector(ray.point, plane.point);

    float scaleFactor =
        rayToPlaneVector.dotProduct(plane.normal) / ray.vector.dotProduct(plane.normal);

    return ray.point.translate(ray.vector.scale(scaleFactor));
  }

  public static class Point {
    public final float x, y, z;

    public Point(float x, float y) {
      this.x = x;
      this.y = y;
      this.z = 0f;
    }

    public Point(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public Point translateY(float distance) {
      return new Point(x, y + distance, z);
    }

    public Point translate(Vector vector) {
      return new Point(x + vector.x, y + vector.y, z + vector.z);
    }

    public float distanceTo(Point to) {
      return new Vector(this, to).length();
    }
  }

  public static class Circle {
    public final Point center;
    public final float radius;

    public Circle(Point center, float radius) {
      this.center = center;
      this.radius = radius;
    }

    public Circle scale(float scale) {
      return new Circle(center, radius * scale);
    }

    public boolean intersects(Circle with) {
      return center.distanceTo(with.center) <= radius + with.radius;
    }

    public boolean intersectsSoft(Circle with) {
      return center.distanceTo(with.center) <= (radius + with.radius) * 0.75f;
    }
  }

  public static class Cylinder {
    public final Point center;
    public final float radius;
    public final float height;

    public Cylinder(Point center, float radius, float height) {
      this.center = center;
      this.radius = radius;
      this.height = height;
    }
  }

  public static class Ray {
    public final Point point;
    public final Vector vector;

    public Ray(Point point, Vector vector) {
      this.point = point;
      this.vector = vector;
    }
  }

  public static class Vector {
    public final float x, y, z;

    public Vector(float x, float y) {
      this(x, y, 0f);
    }

    public Vector(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public Vector(Point from, Point to) {
      x = to.x - from.x;
      y = to.y - from.y;
      z = to.z - from.z;
    }

    public float length() {
      return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector crossProduct(Vector other) {
      return new Vector((y * other.z) - (z * other.y), (z * other.x) - (x * other.z),
          (x * other.y) - (y * other.x));
    }

    public float dotProduct(Vector other) {
      return x * other.x + y * other.y + z * other.z;
    }

    public Vector scale(float f) {
      return new Vector(x * f, y * f, z * f);
    }

    public Vector extend2d(float add) {
      return new Vector(x * (1 + Math.abs(add / x)), y * (1 + Math.abs(add / y)));
    }

    public Vector add(Vector other) {
      return new Vector(x + other.x, y + other.y, z + other.z);
    }

    public Vector rotateRandom(Random r) {

      float angle = r.nextFloat() * 2 * (float) Math.PI;
      return rotate(angle);
    }

    public Vector rotate(float angle) {
      return new Vector(x * (float) Math.cos(angle) - y * (float) Math.sin(angle),
          x * (float) Math.sin(angle) + y * (float) Math.cos(angle));
    }

    public float angle() {
      return (float) Math.atan2(y, x);
    }
  }

  public static class Sphere {
    public final Point center;
    public final float radius;

    public Sphere(Point center, float radius) {
      this.center = center;
      this.radius = radius;
    }
  }

  public static class Plane {
    public final Point point;
    public final Vector normal;

    public Plane(Point point, Vector normal) {
      this.point = point;
      this.normal = normal;
    }
  }
}
