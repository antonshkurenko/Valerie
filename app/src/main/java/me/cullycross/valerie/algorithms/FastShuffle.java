package me.cullycross.valerie.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/19/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class FastShuffle<T> extends AbstractShuffleAlgorithm<T> {

  @Override public List<T> shuffle(List<T> list, ShuffleCallback callback) {

    final List<T> sourceCopy = new ArrayList<>(list);

    int n = sourceCopy.size(), i;

    while (n > 0) {
      i = (int) Math.round(Math.floor(mRandom.nextDouble() * n--));

      if (callback != null) {
        callback.onChooseIndex(i);
      }

      Collections.swap(sourceCopy, n, i);
      if (callback != null) {
        callback.onShuffle(n, i);
      }
    }

    return sourceCopy;
  }
}
