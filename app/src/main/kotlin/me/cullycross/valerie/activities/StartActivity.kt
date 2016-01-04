package me.cullycross.valerie.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.Bind
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.bindView
import me.cullycross.valerie.R
import me.cullycross.valerie.algorithms.*
import timber.log.Timber
import java.util.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        ButterKnife.bind(this)
    }
}
