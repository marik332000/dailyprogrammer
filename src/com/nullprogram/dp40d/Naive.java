package com.nullprogram.dp40d;

import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * A naive, brute-force solver that compares every point to every
 * other point.
 */
@RequiredArgsConstructor
public final class Naive implements Solver {

    private final List<Point> points;

    @Override
    public Pair call() {
        float best = 2f;
        Pair closest = null;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point a = points.get(i);
                Point b = points.get(j);
                float dist = a.dist2(b);
                if (dist < best) {
                    best = dist;
                    closest = new Pair(a, b);
                }
            }
        }
        return closest;
    }
}
