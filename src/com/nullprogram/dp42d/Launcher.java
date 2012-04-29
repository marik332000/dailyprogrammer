package com.nullprogram.dp42d;

import com.nullprogram.dp.TimedCallable;
import java.io.File;
import java.io.IOException;

/**
 * The main class.
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
        try {
            Graph g = Graph.fromFile(new File(args[0]));
            GraphSolver solver = new GraphSolver(g);
            double time = new TimedCallable(solver).call();
            System.out.println("Took " + time + " seconds.");
            System.out.println(solver.getIslands().size() + " islands:");
            for (Graph island : solver.getIslands()) {
                System.out.println(island);
            }
        } catch (IOException e) {
            System.err.println("error: " + e.getMessage());
            System.exit(-1);
        }
    }
}
