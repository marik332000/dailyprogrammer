package com.nullprogram.dp44d;

import java.math.BigInteger;

/**
 * The main program, which accepts exactly two command line arguments.
 * BigInteger wasn't strictly needed to solve the challenge since it
 * all fits inside long, but I like making a general solution.
 */
public final class Launcher {

    /**
     * Hidden constructor.
     */
    private Launcher() {
    }

    /**
     * The main method.
     * @param args  command line arguments
     */
    public static void main(final String[] args) {
        if (args.length != 2) {
            System.err.println("error: requires 2 arguments");
            System.exit(1);
        }
        BigInteger min = new BigInteger(args[0]);
        BigInteger max = new BigInteger(args[1]).add(min);
        Solver solver = new Simple(min, max);
        solver.run();
        System.out.println(solver);
    }
}
