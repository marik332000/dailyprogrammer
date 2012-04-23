package com.nullprogram.dp40d;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Naive implements Solver {
    private final List<Point> points;

    @Override
    public Point[] solve() {
        float best = 2f;
        Point[] closest = null;
        for (Point a : points) {
            for (Point b : points) {
                if (a != b) {
                    float dist = a.dist(b);
                    if (dist < best) {
                        best = dist;
                        closest = new Point[] {a, b};
                    }
                }
            }
        }
        return closest;
    }
}
