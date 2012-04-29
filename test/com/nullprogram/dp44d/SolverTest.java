package com.nullprogram.dp44d;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Check the solvers against the two sample sets.
 */
public class SolverTest {

    @Test
    public void smallTest() {
        /* Test parameters. */
        BigInteger a = BigInteger.valueOf(1234);
        BigInteger b = BigInteger.valueOf(100);
        long count = 15;
        long sum = 19339;
        check(count, sum, new Simple(a, a.add(b)));
        check(count, sum, new ParallelSimple(a, a.add(b)));
    }

    @Test
    public void mediumTest() {
        /* Test parameters. */
        BigInteger a = Factorial.get(9);
        BigInteger b = Factorial.get(8);
        long count = 3124;
        long sum = 1196464560L;
        check(count, sum, new Simple(a, a.add(b)));
        check(count, sum, new ParallelSimple(a, a.add(b)));
    }

    private void check(long count, long sum, Solver solver) {
        solver.run();
        assertEquals(sum, solver.getSum().longValue());
        assertEquals(count, (long) solver.getCount());
    }
}
