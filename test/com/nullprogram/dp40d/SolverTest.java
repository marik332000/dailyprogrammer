package com.nullprogram.dp40d;

import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolverTest {

    /* The test problem set. */
    private static final int SEED = 8;
    private static final int COUNT = 10000;
    private static final int DIMENSIONS = 2;
    private static final float SOLUTION = 5.439364514359113E-9f;
    private static final List<Point> points =
        new PointFactory(new Random(SEED), DIMENSIONS).generate(COUNT);

    /**
     * Test the naive solver.
     */
    @Test
    public void naiveTest() {
        Solver naive = new Naive(points);
        checkSolver(naive);
    }

    /**
     * Test the planar solver.
     */
    @Test
    public void planarTest() {
        Solver planar = new Planar(points);
        checkSolver(planar);
    }

    private void checkSolver(Solver solver) {
        Pair best = solver.solve();
        float dist2 = best.getA().dist2(best.getB());
        assertEquals(SOLUTION, dist2, 1e-16f);
    }
}
