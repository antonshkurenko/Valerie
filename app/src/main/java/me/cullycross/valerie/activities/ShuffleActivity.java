package me.cullycross.valerie.activities;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import me.cullycross.valerie.R;
import me.cullycross.valerie.graphics.renderers.ShuffleRenderer;
import me.cullycross.valerie.mechanics.LogicThread;
import me.cullycross.valerie.mechanics.Machinarium;
import me.cullycross.valerie.mechanics.directors.Director;
import me.cullycross.valerie.mechanics.directors.HorizontalLineDirector;
import me.cullycross.valerie.mechanics.objects.BaseObject;
import me.cullycross.valerie.utils.Point;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/11/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class ShuffleActivity extends BaseRenderActivity {

  private Machinarium mMechanics;

  @Override protected GLSurfaceView.Renderer getRenderer() {
    final List<BaseObject> objects = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      objects.add(new BaseObject());
    }

    final Director<BaseObject> director = new HorizontalLineDirector<>(0.04f);

    director.direct(new Point(), objects, null); // todo(tonyshkurenko), 1/29/16:  why no default value for lambda?

    mMechanics = new Machinarium(objects);

    return new ShuffleRenderer(this, mMechanics);
  }

  @Override protected int getLayoutId() {
    return R.layout.activity_shuffle;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);



    final LogicThread thread = new LogicThread(this, mMechanics, mGLSurfaceView);
    thread.start();
  }
}
