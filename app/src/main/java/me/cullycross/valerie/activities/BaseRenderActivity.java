package me.cullycross.valerie.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.cullycross.valerie.R;
import me.cullycross.valerie.graphics.renderers.Abstract2dRenderer;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/11/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public abstract class BaseRenderActivity extends AppCompatActivity {

  protected boolean mRenderSet = false;

  protected SharedPreferences mSharedPreferences;

  @Bind(R.id.valerie_surface_view) GLSurfaceView mGLSurfaceView;

  /**
   * for 2d rendering it's preferable to use {@link Abstract2dRenderer}
   *
   * @return configured renderer
   */
  protected abstract GLSurfaceView.Renderer getRenderer();

  @LayoutRes protected abstract int getLayoutId();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    ButterKnife.bind(this);

    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    initSurface();
  }

  private void initSurface() {
    final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
    final boolean supportEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

    if (supportEs2) {
      //Request an Open ES 2.0 compatible context.
      mGLSurfaceView.setEGLContextClientVersion(2);
      mGLSurfaceView.setRenderer(getRenderer());
      mGLSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

      mRenderSet = true;
    } else {
      Toast.makeText(this, "This device does not support OpenGL ES 2.0.", Toast.LENGTH_LONG).show();
      finish();
    }
  }
}
