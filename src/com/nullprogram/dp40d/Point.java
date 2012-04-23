package com.nullprogram.dp40d;

import java.util.Random;
import lombok.Data;

@Data
public class Point {
    private static final Random RNG = new Random();

    private final float[] coords;

    public Point() {
        this(2);
    }

    public Point(int dims) {
        coords = new float[dims];
        for (int i = 0; i < dims; i++) {
            coords[i] = RNG.nextFloat();
        }
    }

    public int getDimensions() {
        return coords.length;
    }

    public float dist(Point p) {
        float sum = 0;
        for (int i = 0; i < coords.length; i++) {
            float diff = this.coords[i] - p.coords[i];
            sum += diff * diff;
        }
        return sum;
    }
}
