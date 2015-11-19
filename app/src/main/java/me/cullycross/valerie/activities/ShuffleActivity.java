package me.cullycross.valerie.activities;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.cullycross.valerie.R;
import me.cullycross.valerie.views.ValerieSurfaceView;

public class ShuffleActivity extends BaseRenderActivity {

  @Override public GLSurfaceView.Renderer createRenderer() {
    return null;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shuffle);
    ButterKnife.bind(this);
  }
}
