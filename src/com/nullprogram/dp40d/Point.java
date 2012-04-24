package com.nullprogram.dp40d;

import java.lang.StringBuilder;
import java.util.Arrays;
import java.util.Random;
import lombok.EqualsAndHashCode;
import net.jcip.annotations.Immutable;

/**
 * Represents a point in a system of arbitrary dimension. This class
 * is immutable.
 */
@Immutable
@EqualsAndHashCode
public final class Point {
    private final float[] coords;

    /**
     * Randomly generate a new point.
     * @param rng   the random number generator to use
     * @param dims  the number of dimensions for this point
     */
    Point(final Random rng, final int dims) {
        coords = new float[dims];
        for (int i = 0; i < dims; i++) {
            coords[i] = rng.nextFloat();
        }
    }

    /**
     * Get the position for an individual dimension.
     * @param dim  the dimension to query
     * @return the position of this point
     */
    public float get(final int dim) {
        return coords[dim];
    }

    /**
     * Get the dimensions of this point.
     * @return the number of dimensions of this point
     */
    public int getDimensions() {
        return coords.length;
    }

    /**
     * Calculate the squared distance between two points.
     * @param p  the other point
     * @return the distance between this point and the other point squared
     */
    public float dist2(final Point p) {
        float sum = 0;
        for (int i = 0; i < coords.length; i++) {
            float diff = this.coords[i] - p.coords[i];
            sum += diff * diff;
        }
        return sum;
    }

    /**
     * Calculate the distance between two points.
     * @param p  the other point
     * @return the distance between this point and the other point
     */
    public float dist(final Point p) {
        return (float) Math.sqrt(dist2(p));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("(");
        for (int i = 0; i < coords.length; i++) {
            str.append(coords[i]);
            if (i < coords.length - 1) {
                str.append(", ");
            }
        }
        str.append(")");
        return str.toString();
    }
}
