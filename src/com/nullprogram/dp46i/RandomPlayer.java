package com.nullprogram.dp46i;

import java.util.Random;

/**
 * Plays its turns completely at random. (0.02% win rate)
 */
public class RandomPlayer implements Player {

    private static final Random RNG = new Random();

    private final State state = new State();

    @Override
    public int place(int n) {
        int index;
        do {
            index = RNG.nextInt(State.NVALUES);
        } while (state.filled(index));
        state.set(index, n);
        return index;
    }
}
