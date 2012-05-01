package com.nullprogram.dp46i;

/**
 * Keeps track of a game's state.
 */
public class State {

    public static final int NSLOTS = 8;
    public static final int NVALUES = 10;

    private int[] slots = new int[NSLOTS];
    private boolean[] filled = new boolean[NSLOTS];

    /**
     * Set the given slot position.
     * @return true if the game should continue
     */
    public boolean set(int index, int value) {
        if (filled[index]) {
            throw new IllegalStateException("Slot already filled, " + index);
        } else {
            filled[index] = true;
            slots[index] = value;
            /* Check for a loss. */
            for (int j = index + 1; j < NSLOTS; j++) {
                if (filled[j] && slots[j] < value) {
                    return false;
                }
            }
        }
        return true;
    }

    public int get(int index) {
        return slots[index];
    }

    /**
     * Determine if a slot is filled already.
     * @param index  the index to check
     * @return true if already filled
     */
    public boolean filled(int index) {
        if (index < 0 || index >= NSLOTS) {
            return true;
        } else {
            return filled[index];
        }
    }

    /**
     * Determine if the game is in a winning state.
     * @return true for a win
     */
    public boolean won() {
        for (int i = 1; i < NSLOTS; i++) {
            if (slots[i - 1] > slots[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ ");
        for (int i = 0; i < NSLOTS; i++) {
            if (filled[i]) {
                str.append(slots[i]);
            } else {
                str.append('_');
            }
            str.append(' ');
        }
        str.append("]");
        return str.toString();
    }
}
