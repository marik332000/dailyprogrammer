package com.nullprogram.dp44d;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleTest {

    @Test
    public void smallTest() {
        /* Test parameters. */
        long a = 1234;
        long b = 100;
        long count = 15;
        long sum = 19339;
        test(a, b, count, sum);
    }

    @Test
    public void mediumTest() {
        /* Test parameters. */
        long a = Factorial.get(9).longValue();
        long b = Factorial.get(8).longValue();
        long count = 3124;
        long sum = 1196464560L;
        test(a, b, count, sum);
    }

    private void test(long a, long b, long count, long sum) {
        /* Run the test. */
        BigInteger min = BigInteger.valueOf(a);
        BigInteger max = BigInteger.valueOf(b).add(min);
        Solver solver = new Simple(min, max);
        solver.run();

        /* Check results. */
        assertEquals(sum, solver.getSum().longValue());
        assertEquals(count, (long) solver.getCount());
    }
}
