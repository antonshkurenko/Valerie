package me.cullycross.valerie.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.Bind;
import me.cullycross.valerie.R;
import me.cullycross.valerie.views.ValerieSurfaceView;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public abstract class BaseRenderActivity extends AppCompatActivity {

  protected SharedPreferences mSharedPreferences;
  protected boolean mRenderSet = false;

  @Bind(R.id.surface_view) ValerieSurfaceView mSurfaceView;

  public abstract GLSurfaceView.Renderer createRenderer();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    initSurface();
  }

  @Override protected void onResume() {
    super.onResume();

    if (mRenderSet) {
      mSurfaceView.onResume();
    }
  }

  @Override protected void onPause() {
    if (mRenderSet) {
      mSurfaceView.onPause();
    }
    super.onPause();
  }

  private void initSurface() {
    final ActivityManager activityManager =
        (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
    final boolean supportEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

    if (supportEs2) {
      //Request an Open ES 2.0 compatible context.
      mSurfaceView.setEGLContextClientVersion(2);
      mSurfaceView.setRenderer(createRenderer());
      mSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

      mRenderSet = true;
    } else {
      Toast.makeText(this, "This device does not support OpenGL ES 2.0.", Toast.LENGTH_LONG).show();
      finish();
    }
  }
}
