package me.cullycross.valerie.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import me.cullycross.valerie.R;
import me.cullycross.valerie.algorithms.CollectionsApiShuffle;
import me.cullycross.valerie.algorithms.FastShuffle;
import me.cullycross.valerie.algorithms.MediumShuffle;
import me.cullycross.valerie.algorithms.Shuffling;
import me.cullycross.valerie.algorithms.SlowShuffle;
import timber.log.Timber;

public class StartActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);

    ButterKnife.bind(this);

    testShuffle();
  }

  private void testShuffle() {

    final List<Integer> test = new ArrayList<>();

    for (int i = 100; i-- > 0; ) {
      test.add(i);
    }

    Shuffling<Integer> algorithm = new SlowShuffle<>();

    long currentTime = System.nanoTime();

    Timber.d(algorithm.shuffle(test, null).toString());
    long elapsed = System.nanoTime() - currentTime;
    Timber.d("Elapsed time: " + elapsed);

    algorithm = new MediumShuffle<>();

    currentTime = System.nanoTime();
    Timber.d(algorithm.shuffle(test, null).toString());
    elapsed = System.nanoTime() - currentTime;
    Timber.d("Elapsed time: " + elapsed);

    algorithm = new FastShuffle<>();

    currentTime = System.nanoTime();
    Timber.d(algorithm.shuffle(test, null).toString());
    elapsed = System.nanoTime() - currentTime;
    Timber.d("Elapsed time: " + elapsed);

    algorithm = new CollectionsApiShuffle<>();

    currentTime = System.nanoTime();
    Timber.d(algorithm.shuffle(test, null).toString());
    elapsed = System.nanoTime() - currentTime;
    Timber.d("Elapsed time: " + elapsed);
  }
}
