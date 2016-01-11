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
public class CollectionsApiShuffle<T> extends AbstractShuffleAlgorithm<T> {

  @Override public List<T> shuffle(List<T> list, ShuffleCallback callback) {

    // callback ignored, because I can't add callback to system api

    final List<T> sourceCopy = new ArrayList<>(list);

    Collections.shuffle(sourceCopy, mRandom);
    return sourceCopy;
  }
}
