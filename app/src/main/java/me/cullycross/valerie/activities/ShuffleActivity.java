package me.cullycross.valerie.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.cullycross.valerie.R;
import me.cullycross.valerie.views.ShuffleSurfaceView;

public class ShuffleActivity extends AppCompatActivity {

  @Bind(R.id.shuffle_surface_view) ShuffleSurfaceView mShuffleSurfaceView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shuffle);
    ButterKnife.bind(this);
  }
}
