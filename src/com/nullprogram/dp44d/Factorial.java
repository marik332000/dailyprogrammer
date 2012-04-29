package com.nullprogram.dp44d;

import java.math.BigInteger;

/**
 * Just a utility class for the factorial factory method.
 */
public final class Factorial {

    /**
     * Hidden constructor.
     */
    private Factorial() {
    }

    /**
     * Calculate a factorial.
     * @param n  the base number
     * @return the factorial
     */
    public static BigInteger get(final int n) {
        BigInteger prod = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            prod = prod.multiply(BigInteger.valueOf(i));
        }
        return prod;
    }
}
