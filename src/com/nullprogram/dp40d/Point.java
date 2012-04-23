package com.nullprogram.dp40d;

import java.util.Random;
import lombok.Data;

@Data
class Point {
    private final float[] coords;

    Point(Random rng, int dims) {
        coords = new float[dims];
        for (int i = 0; i < dims; i++) {
            coords[i] = rng.nextFloat();
        }
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
    public float dist2(Point p) {
        float sum = 0;
        for (int i = 0; i < coords.length; i++) {
            float diff = this.coords[i] - p.coords[i];
            sum += diff * diff;
        }
        return sum;
    }
}
