package activities

import activities.BaseRenderActivity
import android.content.SharedPreferences
import android.opengl.GLSurfaceView
import android.opengl.GLSurfaceView.Renderer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.Bind
import butterknife.ButterKnife
import butterknife.bindView
import me.cullycross.valerie.R
import me.cullycross.valerie.views.ValerieSurfaceView
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
