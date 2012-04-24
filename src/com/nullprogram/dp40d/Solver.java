package com.nullprogram.dp40d;

/**
 * Implementers of this interface solve the closest pair problem.
 */
public interface Solver {

    /**
     * Solve the problem.
     * @return the closest pair of points
     */
    public Pair solve();
}
