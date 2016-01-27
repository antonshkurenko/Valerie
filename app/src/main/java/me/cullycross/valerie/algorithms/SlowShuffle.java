package me.cullycross.valerie.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/18/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

public class SlowShuffle<T> extends AbstractShuffleAlgorithm<T> {

  @Override public List<T> shuffle(List<T> list, ShuffleCallback callback) {

    final List<T> sourceCopy = new ArrayList<>(list);

    final int size = sourceCopy.size();
    final List<T> copy = new ArrayList<>(size);
    int n = size, i;

    while (n > 0) {

      i = (int) Math.round(Math.floor(mRandom.nextDouble() * size));

      if (callback != null) {
        callback.onChooseIndex(i);
      }

      final T object = sourceCopy.get(i);
      if (object != null) {
        copy.add(object);
        sourceCopy.set(i, null);
        n--;
        if (callback != null) {
          callback.onShuffle(copy.size() - 1, i);
        }
      }
    }

    return copy;
  }
}
