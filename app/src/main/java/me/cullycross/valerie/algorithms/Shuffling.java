package me.cullycross.valerie.algorithms;

import java.util.List;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/18/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

// todo(tonyshkurenko), 11/19/15: investigate about speed and memory of every algorithm
public interface Shuffling<T> {

    /**
     * shuffle!
     *
     * @param list of objects to shuffle
     * @return result shuffled list
     */

    List<T> shuffle(List<T> list);
}
