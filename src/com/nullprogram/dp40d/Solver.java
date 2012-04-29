package com.nullprogram.dp40d;

import java.util.concurrent.Callable;

/**
 * Implementers of this interface solve the closest pair
 * problem. <i>No</i> computational work (sorting, etc.) should be
 * done in the constructor, just assignments and invariant checks.
 */
public interface Solver extends Callable<Pair>{

    /**
     * Solve the problem.
     * @return the closest pair of points
     */
    @Override
    Pair call();
}
