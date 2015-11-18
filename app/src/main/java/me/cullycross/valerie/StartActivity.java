package me.cullycross.valerie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import me.cullycross.valerie.algorithms.CollectionsApiShuffle;
import me.cullycross.valerie.algorithms.FastShuffle;
import me.cullycross.valerie.algorithms.MediumShuffle;
import me.cullycross.valerie.algorithms.Shuffling;
import me.cullycross.valerie.algorithms.SlowShuffle;

public class StartActivity extends AppCompatActivity {

  private static final String TAG = StartActivity.class.getSimpleName();

  final List<Integer> mTest = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);

    for(int i = 100; i -->0 ;) {
      mTest.add(i);
    }

    Shuffling<Integer> algorithm = new SlowShuffle<>();

    long currentTime = System.nanoTime();

    Log.d(TAG, algorithm.shuffle(mTest, null).toString());
    long elapsed = System.nanoTime() - currentTime;
    Log.d(TAG, "Elapsed time: " + elapsed);

    algorithm = new MediumShuffle<>();

    currentTime = System.nanoTime();
    Log.d(TAG, algorithm.shuffle(mTest, null).toString());
    elapsed = System.nanoTime() - currentTime;
    Log.d(TAG, "Elapsed time: " + elapsed);


    algorithm = new FastShuffle<>();

    currentTime = System.nanoTime();
    Log.d(TAG, algorithm.shuffle(mTest, null).toString());
    elapsed = System.nanoTime() - currentTime;
    Log.d(TAG, "Elapsed time: " + elapsed);


    algorithm = new CollectionsApiShuffle<>();

    currentTime = System.nanoTime();
    Log.d(TAG, algorithm.shuffle(mTest, null).toString());
    elapsed = System.nanoTime() - currentTime;
    Log.d(TAG, "Elapsed time: " + elapsed);
  }
}
