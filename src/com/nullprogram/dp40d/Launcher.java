package com.nullprogram.dp40d;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Launcher {
    public static void main(String[] args) {
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
        Point[] solution = solver.solve();
        double time = (System.nanoTime() - start) / 1000000000.0d;

        /* Print results */
        out.println(String.format("Time:\t\t%.3f seconds", time));
        out.println("Distance:\t" + Math.sqrt(solution[0].dist2(solution[1])));
        out.println(solution[0]);
        out.println(solution[1]);
    }
}
