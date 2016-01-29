package me.cullycross.valerie.mechanics

import me.cullycross.valerie.algorithms.Shuffling
import me.cullycross.valerie.mechanics.actions.Action
import me.cullycross.valerie.mechanics.actions.TranslateAction
import me.cullycross.valerie.mechanics.objects.BaseObject
import java.util.*

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/27/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
class Machinarium(val objects: List<BaseObject>) : Shuffling.ShuffleCallback {

    private companion object {
        private const val ALGORITHM_STEP_DELAY: Long = 1000L
    }

    /**
     * Requests array is responsible for the invoking every step of the algorithm
     *
     * Polling an element every [ALGORITHM_STEP_DELAY] ms and create a corresponding action, to show it
     */
    private val requests: Queue<() -> Unit> = LinkedList()

    /**
     * Keeps all scene actions
     */
    private val actions = ArrayList<Action>()

    private var passedTime: Long = 0L;

    override fun onShuffle(newIndex: Int, pickedFromIndex: Int) {

        val action = TranslateAction(
                objects[pickedFromIndex],
                objects[newIndex].position.translateY(-0.5f),
                5000L,
                object : Action.Callback {
                    override fun onActionStart() {
                        throw UnsupportedOperationException()
                    }

                    override fun onActionEnd() {
                        throw UnsupportedOperationException()
                    }
                })

        actions.add(action)

        requests.add {
            action.start()
        }
    }

    override fun onChooseIndex(index: Int) {
        requests.add {
            objects[index] // todo(tonyshkurenko), 1/27/16: do something, color or etc, shake?
        }
    }

    fun step(delta: Long):Boolean {
        actions.forEach { it.step(delta) }

        return false // true if the end
    }
}