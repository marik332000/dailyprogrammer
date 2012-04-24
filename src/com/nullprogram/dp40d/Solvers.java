package com.nullprogram.dp40d;

import java.util.List;

/**
 * Solver factory for creating solvers by name (i.e. command line argument).
 */
public final class Solvers {

    /**
     * Hidden constructor.
     */
    private Solvers() {
    }

    /**
     * Create a new solver by name.
     * @param name    the name of the solver
     * @param points  the list of points to solve for
     * @return the requested solver
     */
    public static Solver create(final List<Point> points, final String name) {
        if ("naive".equals(name.toLowerCase())) {
            return new Naive(points);
        } else if ("planar".equals(name.toLowerCase())) {
            return new Planar(points);
        } else {
            throw new IllegalArgumentException("Invalid solver: " + name);
        }
    }
}
