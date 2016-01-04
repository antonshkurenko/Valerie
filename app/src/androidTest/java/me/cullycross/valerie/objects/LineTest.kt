package me.cullycross.valerie.objects

import junit.framework.TestCase
import me.cullycross.valerie.utils.Point

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/4/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class LineTest : TestCase() {

    lateinit var line: Line

    override fun setUp() {
        super.setUp()

        //line = Line(Point(0f, 0f), 9f, 0f)
        line = Line(Point(0f, 0f), Point(9f, 9f))
    }

    fun testTranslate() {
        line.translate(Point(1f, 1f))

        assertEquals("New end point x", 10f, line.getEnd().x)
        assertEquals("New end point y", 10f, line.getEnd().y)

        line.translate(Point(-1f, -1f))

        assertEquals("New end point x", 8f, line.getEnd().x)
        assertEquals("New end point y", 8f, line.getEnd().y)
    }
}