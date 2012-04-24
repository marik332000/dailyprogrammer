package com.nullprogram.dp40d;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;

/**
 * Create points from a specified random number generator.
 */
@RequiredArgsConstructor
public final class PointFactory {
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
    public List<Point> generate(final int count) {
        List<Point> points = new ArrayList<Point>(count);
        for (int i = 0; i < count; i++) {
            points.add(generate());
        }
        return points;
    }

    /**
     * Write the points to a file.
     * @param file    the file to write
     * @param points  the points to write
     * @throws IOException for file issues
     */
    public static void toFile(final File file, final Iterable<Point> points)
        throws IOException {
        PrintWriter out = new PrintWriter(file);
        for (Point p : points) {
            for (int i = 0; i < p.getDimensions(); i++) {
                out.print(p.get(i));
                if (i < p.getDimensions() - 1) {
                    out.print(" ");
                }
            }
            out.println();
        }
        out.close();
    }

    /**
     * Read a list of points from a file.
     * @param file  the file to read
     * @return the list of points
     * @throws IOException for file issues
     */
    public static List<Point> fromFile(final File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        List<Point> points = new ArrayList<Point>();
        String line;
        while ((line = in.readLine()) != null) {
            String[] coordstr = line.split("[\\s,]+");
            float[] coords = new float[coordstr.length];
            for (int i = 0; i < coordstr.length; i++) {
                coords[i] = Float.parseFloat(coordstr[i]);
            }
            points.add(new Point(coords));
        }
        in.close();
        return points;
    }
}
