package com.nullprogram.dp46i;

import java.util.LinkedList;
import java.util.List;

/**
 * Plays rya11111's strategy. (10.3% win rate)
 */
public final class RyaPlayer implements Player {

    private final List<Integer> remaining = new LinkedList<Integer>();

    /**
     * A new player.
     */
    public RyaPlayer() {
        for (int i = 0; i < State.NSLOTS; i++) {
            remaining.add(i);
        }
    }

    @Override
    public int place(final int n) {
        int rindex = (int) (remaining.size() * n * (1.0 / State.NVALUES));
        Integer index = remaining.get(rindex);
        remaining.remove(rindex);
        return index;
    }
}
