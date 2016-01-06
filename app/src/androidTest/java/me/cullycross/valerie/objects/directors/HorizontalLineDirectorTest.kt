package me.cullycross.valerie.objects.directors

import android.test.AndroidTestCase
import me.cullycross.valerie.objects.Line
import me.cullycross.valerie.utils.Point
import timber.log.Timber
import java.util.*

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/7/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class HorizontalLineDirectorTest : AndroidTestCase() {

    var director: Director<Line> = HorizontalLineDirector(1f)

    override fun setUp() {
        super.setUp()

        Timber.plant(Timber.DebugTree())
    }

    fun testDirect() {

        val list: MutableList<Line> = ArrayList()

        for (i in 0..19) {
            list.add(Line())
        }

        director.direct(Point(1f, 2f), list)

        Timber.d("Directed list(${list.size}): ${list.toString()}")

        // 1 - 1 * (20 / 2 + 0.5) = -9.5
        assertEquals("Even number", -9.5f, list[0].position.x)
        assertEquals("Y position", 2f, list[0].position.y)

        list.clear()

        for (i in 0..22) {
            list.add(Line())
        }

        director.direct(Point(1f, 2f), list)

        Timber.d("Directed list(${list.size}): ${list.toString()}")

        // 1 - 1 * (23 / 2) = -10.5
        assertEquals("Odd number", -10.5f, list[0].position.x)
        assertEquals("Y position", 2f, list[0].position.y)

        Timber.d(list.toString())
    }
}