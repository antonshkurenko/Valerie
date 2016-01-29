package me.cullycross.valerie.mechanics

import me.cullycross.valerie.algorithms.Shuffling
import me.cullycross.valerie.mechanics.actions.Action
import me.cullycross.valerie.mechanics.actions.TranslateAction
import me.cullycross.valerie.mechanics.objects.BaseObject
import timber.log.Timber
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

    private var passedTime: Long = 0L
    private var timeSinceLastAction = 0L

    override fun onShuffle(newIndex: Int, pickedFromIndex: Int) {

        val action = TranslateAction(
                objects[pickedFromIndex],
                objects[newIndex].position.translateY(-0.5f),
                5000L)

        actions.add(action)

        requests.add {
            action.start()
        }
    }

    override fun onChooseIndex(index: Int) {
        val choosenObject = objects[index]
        val action = TranslateAction(
                choosenObject,
                choosenObject.position.translateY(0.1f),
                250L,
                object : Action.Callback {
                    override fun onActionStart() {
                        // ignored
                    }

                    override fun onActionEnd() {
                        val actionBack = TranslateAction(
                                choosenObject,
                                choosenObject.position.translateY(-0.1f),
                                250L)
                        actions.add(actionBack)
                        actionBack.start()
                    }
                })

        requests.add {
            actions.add(action)
            action.start()
        }
    }


    var tempCounter: Int = 0

    fun step(delta: Long): Boolean {

        if (passedTime == 0L || (actions.size == 0 && requests.size == 0)) {
            tempCounter++
            init()
        }

        passedTime += delta
        timeSinceLastAction += delta

        if (timeSinceLastAction >= ALGORITHM_STEP_DELAY / tempCounter) {

            val request = requests.poll()
            if (request != null) {
                request.invoke()
                timeSinceLastAction = 0L
            }
        }

        Timber.d("Action::step, actions.size() = %d", actions.size)
        actions.forEach {
            it.step(delta)
            if (!it.isActive()) {
                actions.remove(it)
            }
        }

        return false // true if the end
    }

    private fun init() {

        objects.forEach {
            val action = TranslateAction(
                    it,
                    it.position.translateY(0.1f),
                    250L,
                    object : Action.Callback {
                        override fun onActionStart() {
                            // ignored
                        }

                        override fun onActionEnd() {
                            val actionBack = TranslateAction(
                                    it,
                                    it.position.translateY(-0.1f),
                                    250L)
                            actions.add(actionBack)
                            actionBack.start()
                        }
                    })

            requests.add {
                actions.add(action)
                action.start()
            }
        }
    }
}