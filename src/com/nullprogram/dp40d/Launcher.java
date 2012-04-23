package com.nullprogram.dp40d;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        /* Configure */
        PrintStream out = System.out;
        int count = 10000;
        out.println("Size:\t\t" + count + " points");

        /* Generate points */
        List<Point> points = new ArrayList<Point>(count);
        for (int i = 0; i < count; i++) {
            points.add(new Point(2));
        }

        /* Solve */
        long start = System.nanoTime();
        Solver solver = new Naive(points);
        Point[] solution = solver.solve();
        double time = (System.nanoTime() - start) / 1000000000.0d;

        /* Print results */
        out.println(String.format("Time:\t\t%.3f seconds", time));
        out.println("Distance:\t" + solution[0].dist(solution[1]));
        out.println(solution[0]);
        out.println(solution[1]);
    }
}
