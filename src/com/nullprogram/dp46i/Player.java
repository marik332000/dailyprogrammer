package com.nullprogram.dp46i;

public interface Player {
    /**
     * Return the placement for the next digit.
     * @param n  the next digit
     * @return the placement index, or less than 0 for a forfeit
     */
    int place(int n);
}
