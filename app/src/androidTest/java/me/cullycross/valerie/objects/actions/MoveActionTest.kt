package me.cullycross.valerie.objects.actions

import android.test.AndroidTestCase
import android.util.Log
import me.cullycross.valerie.objects.BaseObject
import me.cullycross.valerie.utils.Point
import timber.log.Timber

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/3/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class MoveActionTest : AndroidTestCase() {

    var action: MoveAction? = null

    override fun setUp() {
        super.setUp()

        Timber.plant(Timber.DebugTree())

        action = object: MoveAction(object : BaseObject(Point(0f, 0f)) {

        }, Point(25f, 25f), 5000, object : Action.Callback {
            override fun onActionStart() {
                Timber.d("Action start!")
            }

            override fun onActionEnd() {
                Timber.d("Action end!")
            }
        }) {
            override fun onStep(delta: Long) {
                super.onStep(delta)

                Timber.d("Current object position is: %fx, %fy", obj.position.x, obj.position.y)
            }
        }
    }

    fun testStart() {
        assertFalse("isActive() false", action!!.isActive())

        action!!.start()

        assertTrue("isActive() true", action!!.isActive())
    }

    fun testStep() {
        action!!.start()

        val HUNDRED_MS = 100L;

        for(i in 1..49) {
            action!!.step(HUNDRED_MS)
        }

        assertTrue("isActive() true", action!!.isActive())

        action!!.step(HUNDRED_MS)

        assertFalse("isActive() false", action!!.isActive())
    }

    fun testIsActive() {

        assertFalse("isActive() false", action!!.isActive())

        action!!.start()

        assertTrue("isActive() true", action!!.isActive())
    }
}