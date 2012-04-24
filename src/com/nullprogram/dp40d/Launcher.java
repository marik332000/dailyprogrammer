package com.nullprogram.dp40d;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;

/**
 * Houses the main method and handles command line arguments.
 */
public final class Launcher {

    private static final double NANO = 1000000000.0;

    /** Hidden constructor. */
    private Launcher() {
    }

    /**
     * The main method.
     * @param args  command line arguments
     */
    public static void main(final String[] args) {
        /* Configure */
        PrintStream out = System.out;
        int count = 10000;
        int dimensions = 2;
        Random rng = new Random();

        /* Generate points */
        out.println("Size:\t\t" + count + " points");
        PointFactory factory = new PointFactory(rng, dimensions);
        List<Point> points = factory.generate(count);

        /* Solve */
        long start = System.nanoTime();
        Solver solver = new Naive(points);
        Pair solution = solver.solve();
        double time = (System.nanoTime() - start) / NANO;

        /* Print results */
        out.println(String.format("Time:\t\t%.3f seconds", time));
        out.println("Distance:\t" + solution.getLength());
        out.println(solution);
    }
}
