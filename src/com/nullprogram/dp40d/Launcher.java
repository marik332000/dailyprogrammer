package com.nullprogram.dp40d;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;

/**
 * Houses the main method and handles command line arguments.
 */
public final class Launcher {

    private static final double NANO = 1000000000.0;
    private static final int DEFAULT_COUNT = 10000;

    @Parameter(names = { "--seed", "-s" },
               description = "Point generator seed.")
    private int seed = new Random().nextInt();

    @Parameter(names = "-n", description = "Number of points.")
    private int count = DEFAULT_COUNT;

    @Parameter(names = { "--dims", "-d" },
               description = "Number of dimensions to the points.")
    private int dimensions = 2;

    @Parameter(names = { "--algo" },
               description = "Solver algorithm to use (naive, planar).")
    private String algo = "naive";

    @Parameter(names = { "--to-file" },
               description = "Save the points to a file.")
    private String toFile;

    @Parameter(names = { "--from-file" },
               description = "Read points from a file rather than generate.")
    private String fromFile;

    @Parameter(names = { "--help" },
               description = "Print this usage information.")
    private boolean usage = false;

    /** Hidden constructor. */
    private Launcher() {
    }

    /**
     * The main method.
     * @param args  command line arguments
     */
    public static void main(final String[] args) {
        /* Configure */
        Launcher params = new Launcher();
        try {
            JCommander jc = new JCommander(params, args);
            if (params.usage) {
                jc.usage();
                System.exit(0);
            }
        } catch (ParameterException e) {
            System.out.println("error: " + e.getMessage());
            System.exit(1);
        }
        Random rng = new Random(params.seed);
        PrintStream out = System.out;

        /* Generate points */
        List<Point> points = null;
        if (params.fromFile == null) {
            PointFactory factory = new PointFactory(rng, params.dimensions);
            points = factory.generate(params.count);
        } else {
            try {
                points = PointFactory.fromFile(new File(params.fromFile));
            } catch (IOException e) {
                System.out.println("error: " + e.getMessage());
                System.exit(1);
            }
        }
        if (params.toFile != null) {
            try {
                PointFactory.toFile(new File(params.toFile), points);
            } catch (IOException e) {
                System.out.println("error: " + e.getMessage());
                System.exit(1);
            }
        }

        try {
            /* Solve */
            long start = System.nanoTime();
            Solver solver = Solvers.create(points, params.algo);
            Pair solution = solver.solve();
            double time = (System.nanoTime() - start) / NANO;

            /* Print results */
            out.println("Size:\t\t" + params.count + " points");
            out.println(String.format("Time:\t\t%.3f seconds", time));
            out.println("Distance:\t" + solution.getLength());
            out.println("A:\t\t" + solution.getA());
            out.println("B:\t\t" + solution.getB());

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid algorithm: " + params.algo);
            System.exit(1);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            System.exit(1);
        }
    }
}
