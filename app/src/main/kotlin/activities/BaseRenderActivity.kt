package activities

import android.app.ActivityManager
import android.content.Context
import android.content.SharedPreferences
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.bindView
import me.cullycross.valerie.R
import me.cullycross.valerie.views.ValerieSurfaceView
import timber.log.Timber

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
abstract class BaseRenderActivity : AppCompatActivity() {

    protected var renderSet = false

    protected lateinit var sharedPreferences: SharedPreferences

    internal val surfaceView: ValerieSurfaceView by bindView(R.id.valerie_surface_view)

    /**
     * for 2d rendering it's preferable to use [Abstract2dRenderer]

     * @return configured renderer
     */
    abstract fun createRenderer(): GLSurfaceView.Renderer? // todo(tonyshkurenko), 11/22/15:  later set this to not null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        Timber.d(sharedPreferences.toString())
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        initSurface()
    }

    override fun onResume() {
        super.onResume()

        if (renderSet) {
            surfaceView.onResume()
        }
    }

    override fun onPause() {
        if (renderSet) {
            surfaceView.onPause()
        }
        super.onPause()
    }

    private fun initSurface() {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = activityManager.deviceConfigurationInfo
        val supportEs2 = configurationInfo.reqGlEsVersion >= 131072

        if (supportEs2) {
            //Request an Open ES 2.0 compatible context.
            surfaceView.setEGLContextClientVersion(2)
            //surfaceView.setRenderer(createRenderer())
            surfaceView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY

            renderSet = true
        } else {
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
