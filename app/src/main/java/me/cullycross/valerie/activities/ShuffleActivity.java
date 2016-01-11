package me.cullycross.valerie.activities;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PersistableBundle;
import me.cullycross.valerie.R;
import me.cullycross.valerie.graphics.renderers.ShuffleRenderer;
import timber.log.Timber;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/11/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class ShuffleActivity extends BaseRenderActivity {

  @Override protected GLSurfaceView.Renderer getRenderer() {
    return new ShuffleRenderer(this);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_shuffle;
  }
}
