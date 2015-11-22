package activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.ButterKnife
import butterknife.bindView
import me.cullycross.valerie.R
import me.cullycross.valerie.algorithms.*
import timber.log.Timber
import java.util.*

class StartActivity : AppCompatActivity() {

    val textView : TextView by bindView(R.id.hello_world)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        ButterKnife.bind(this)

        Timber.d(textView.text.toString())

        testShuffle()
    }

    private fun testShuffle() {

        val test = ArrayList<Int>()

        var i = 100
        while (i-- > 0) {
            test.add(i)
        }

        var algorithm: Shuffling<Int> = SlowShuffle()

        var currentTime = System.nanoTime()

        Timber.d(algorithm.shuffle(test, null).toString())
        var elapsed = System.nanoTime() - currentTime
        Timber.d("Elapsed time: " + elapsed)

        algorithm = MediumShuffle<Int>()

        currentTime = System.nanoTime()
        Timber.d(algorithm.shuffle(test, null).toString())
        elapsed = System.nanoTime() - currentTime
        Timber.d("Elapsed time: " + elapsed)

        algorithm = FastShuffle<Int>()

        currentTime = System.nanoTime()
        Timber.d(algorithm.shuffle(test, null).toString())
        elapsed = System.nanoTime() - currentTime
        Timber.d("Elapsed time: " + elapsed)

        algorithm = CollectionsApiShuffle<Int>()

        currentTime = System.nanoTime()
        Timber.d(algorithm.shuffle(test, null).toString())
        elapsed = System.nanoTime() - currentTime
        Timber.d("Elapsed time: " + elapsed)
    }
}
