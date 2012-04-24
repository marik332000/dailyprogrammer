package com.nullprogram.dp40d;

import lombok.Data;
import net.jcip.annotations.Immutable;

/**
 * Represents a pair of points, possibly the solution to the closest
 * pair problem. This class is immutable.
 */
@Data
@Immutable
public final class Pair implements Comparable<Pair> {

    /** A special pair that is an infinite distance apart. */
    public static final Pair INFINITY = new Pair(Float.POSITIVE_INFINITY);

    private final Point a;
    private final Point b;
    private final float length;

    /**
     * Create a new pair from two points.
     * @param a  the first point
     * @param b  the second point
     */
    public Pair(final Point a, final Point b) {
        this.a = a;
        this.b = b;
        this.length = a.dist(b);
    }

    /**
     * Used for creating the special infinity pair.
     * @param length  the specified pair length
     */
    private Pair(final float length) {
        this.a = null;
        this.b = null;
        this.length = length;
    }

    @Override
    public int compareTo(final Pair p) {
        return (int) Math.signum(length - p.length);
    }
}
