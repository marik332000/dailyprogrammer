package com.nullprogram.dp40d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Planar implements Solver {

    private static final Comparator<Point> XSORT = new DimensionComparator(0);
    private static final Comparator<Point> YSORT = new DimensionComparator(1);

    private final List<Point> points;

    public Planar(List<Point> points) {
        this.points = points;
        if (points.get(0).getDimensions() != 2) {
            throw new RuntimeException("Planar only solves for 2 dimensions.");
        }
    }

    @Override
    public Pair solve() {
        /* Work with two sorted lists instead. */
        List<Point> xpoints = new ArrayList<Point>(points);
        Collections.sort(xpoints, XSORT);
        List<Point> ypoints = new ArrayList<Point>(points);
        Collections.sort(ypoints, YSORT);

        return divide(xpoints, ypoints);
    }

    private Pair divide(List<Point> x, List<Point> y) {
        if (x.size() <= 1) {
            return Pair.INFINITY;
        } else if (x.size() == 2) {
            return new Pair(x.get(0), x.get(1));
        }

        /* Divide */
        float m = x.get(x.size() / 2).get(0);
        List<Point> xleft = x.subList(0, x.size() / 2);
        List<Point> xright = x.subList(x.size() / 2, x.size());
        List<Point> yleft = new ArrayList<Point>();
        List<Point> yright = new ArrayList<Point>();
        for (Point p : x) {
            if (p.get(0) < m) {
                yleft.add(p);
            } else {
                yright.add(p);
            }
        }

        /* Conquer */
        Pair dleft = divide(xleft, yleft);
        Pair dright = divide(xright, yright);
        Pair best = dleft;
        if (dright.getLength() < dleft.getLength()) {
            best = dright;
        }

        /* Merge. */
        for (Point lp : yleft) {
            if (m - lp.get(0) < best.getLength()) {
                for (Point rp : yright) {
                    if (rp.get(0) - m < best.getLength()) {
                        if (lp.dist(rp) < best.getLength()) {
                            best = new Pair(lp, rp);
                        }
                    }
                }
            }
        }
        return best;
    }
}
