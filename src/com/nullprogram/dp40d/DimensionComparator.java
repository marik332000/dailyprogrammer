package com.nullprogram.dp40d;

import java.util.Comparator;
import lombok.Data;

/**
 * Sort a collection of points by a particular dimension.
 */
@Data
final class DimensionComparator implements Comparator<Point> {

    private final int dimension;

    @Override
    public int compare(final Point a, final Point b) {
        return (int) Math.signum(a.get(dimension) - b.get(dimension));
    }
}
