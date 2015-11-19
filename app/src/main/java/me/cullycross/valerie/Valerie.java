package me.cullycross.valerie;

import android.app.Application;
import timber.log.Timber;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/20/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class Valerie extends Application {

  @Override public void onCreate() {
    super.onCreate();

    Timber.plant(new Timber.DebugTree());
  }
}
