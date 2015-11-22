package activities

import android.opengl.GLSurfaceView
import android.os.Bundle
import butterknife.ButterKnife
import me.cullycross.valerie.R
import timber.log.Timber

class ShuffleActivity : BaseRenderActivity() {

    override fun createRenderer(): GLSurfaceView.Renderer? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shuffle)

        ButterKnife.bind(this)

        Timber.d(surfaceView.equals(null).toString())
    }
}
