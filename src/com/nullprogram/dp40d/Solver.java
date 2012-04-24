package com.nullprogram.dp40d;

/**
 * Implementers of this interface solve the closest pair
 * problem. <i>No</i> computational work (sorting, etc.) should be
 * done in the constructor, just assignments and invariant checks.
 */
public interface Solver {

    /**
     * Solve the problem.
     * @return the closest pair of points
     */
    Pair solve();
}
