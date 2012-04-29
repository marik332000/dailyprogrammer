package com.nullprogram.dp44d;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleTest {

    @Test
    public void smallTest() {
        /* Test parameters. */
        BigInteger a = BigInteger.valueOf(1234);
        BigInteger b = BigInteger.valueOf(100);
        BigInteger count = BigInteger.valueOf(15);
        BigInteger sum = BigInteger.valueOf(19339);
        test(a, b, count, sum);
    }

    @Test
    public void mediumTest() {
        /* Test parameters. */
        BigInteger a = Factorial.get(9);
        BigInteger b = Factorial.get(8);
        BigInteger count = BigInteger.valueOf(3124);
        BigInteger sum = BigInteger.valueOf(1196464560L);
        test(a, b, count, sum);
    }

    private void test(BigInteger a, BigInteger b,
                     BigInteger count, BigInteger sum) {
        /* Run the test. */
        Solver solver = new Simple(a, a.add(b));
        solver.run();

        /* Check results. */
        assertEquals(sum, solver.getSum());
        assertEquals(count, solver.getCount());
    }
}
