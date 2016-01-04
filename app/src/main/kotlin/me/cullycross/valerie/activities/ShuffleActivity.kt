package me.cullycross.valerie.activities

import activities.BaseRenderActivity
import android.opengl.GLSurfaceView
import android.os.Bundle
import butterknife.ButterKnife
import me.cullycross.valerie.graphics.renderers.Abstract2dRenderer
import me.cullycross.valerie.R
import timber.log.Timber

class ShuffleActivity : BaseRenderActivity() {

    override val renderer: GLSurfaceView.Renderer = object: Abstract2dRenderer(this) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shuffle)

        ButterKnife.bind(this)

        Timber.d(surfaceView.equals(null).toString())
    }
}
