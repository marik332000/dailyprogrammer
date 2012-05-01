package com.nullprogram.dp46i;

/**
 * Tries to fill out the blanks from the middle. (0.45% win rate)
 */
public class DividePlayer implements Player {

    private final State state = new State();

    @Override
    public int place(final int n) {
        int index = State.NSLOTS / 2;
        if (state.filled(index)) {
            int dir = state.get(index) < n ? 1 : -1;
            /* Find the next open slot. */
            while (state.filled(index)) {
                index += dir;
                if (index >= State.NSLOTS || index < 0) {
                    return -1; // Give up
                }
            }
        }
        state.set(index, n);
        return index;
    }
}
