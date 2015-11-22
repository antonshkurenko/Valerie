package me.cullycross.valerie.graphics.objects

import utils.Point
import utils.Vector

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/22/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

class Line(var  from: Point,
           val  startWidth: Float,
           var  to: Point,
           val  endWidth: Float, speed: Vector) : AbstractMovableObject(from, speed) {



}