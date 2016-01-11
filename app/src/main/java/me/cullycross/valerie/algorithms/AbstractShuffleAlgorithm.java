package me.cullycross.valerie.algorithms;

import java.util.Random;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 1/7/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public abstract class AbstractShuffleAlgorithm<T> implements Shuffling<T> {
  protected final Random mRandom = new Random();
}
