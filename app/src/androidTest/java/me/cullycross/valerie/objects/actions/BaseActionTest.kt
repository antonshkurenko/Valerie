package me.cullycross.valerie.objects.actions

import android.test.AndroidTestCase
import timber.log.Timber

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/3/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class BaseActionTest : AndroidTestCase() {

    var action: BaseAction? = null

    override fun setUp() {
        super.setUp()

        Timber.plant(Timber.DebugTree())
        Timber.d("Set up!")
        action = object : BaseAction(5000L, object : Action.Callback {
            override fun onActionStart() {
                Timber.d("Action start!")
            }

            override fun onActionEnd() {
                Timber.d("Action end!")
            }
        }) {
            override fun onStep(delta: Long) {
                Timber.d("On step! Delta: %d, elapsedTime: %d", delta, currentTime - startTime)
            }
        }
    }

    override fun tearDown() {

        action = null

        super.tearDown()
    }

    fun testStart() {
        assertFalse("isActive() false", action!!.isActive())

        action!!.start()

        assertTrue("isActive() true", action!!.isActive())
    }

    fun testStep() {
        action!!.start()

        val HUNDRED_MS = 100L;

        for(i in 0..48) {
            Timber.d("Step #%d", i)
            action!!.step(HUNDRED_MS)
        }

        assertTrue("isActive() true", action!!.isActive())

        Timber.d("Last step")
        action!!.step(HUNDRED_MS)

        assertFalse("isActive() false", action!!.isActive())
    }

    fun testIsActive() {

        assertFalse("isActive() false", action!!.isActive())

        action!!.start()

        assertTrue("isActive() true", action!!.isActive())
    }
}