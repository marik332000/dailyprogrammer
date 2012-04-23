package com.nullprogram.dp40d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PointFactory {
    private final Random rng;
    private final int dimensions;

    /**
     * Generate a single point.
     * @return a freshly generated point
     */
    public Point generate() {
        return new Point(rng, dimensions);
    }

    /**
     * Generate many points.
     * @param count  the number of points to generate
     * @return a list of generated points
     */
    public List<Point> generate(int count) {
        List<Point> points = new ArrayList<Point>(count);
        for (int i = 0; i < count; i++) {
            points.add(generate());
        }
        return points;
    }
}
