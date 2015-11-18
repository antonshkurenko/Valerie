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
public class FastShuffle<T> implements Shuffling<T> {

  final Random mRandom;

  public FastShuffle() {
    mRandom = new Random();
  }

  @Override public List<T> shuffle(List<T> list) {

    final List<T> sourceCopy = new ArrayList<>(list);

    int n = sourceCopy.size(), i;

    while (n > 0) {
      i = (int) Math.round(Math.floor(mRandom.nextDouble() * n--));
      Collections.swap(sourceCopy, n, i);
    }

    return sourceCopy;
  }
}
