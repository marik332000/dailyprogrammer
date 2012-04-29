package com.nullprogram.dp44d;

import java.math.BigInteger;

/**
 * A solver for problem 44. It should be run(), then the results
 * retrieved with this interface's methods.
 */
public interface Solver extends Runnable {
    /**
     * Get the number of primes in range.
     * @return the count
     */
    BigInteger getCount();

    /**
     * Get the sum of all primes in range.
     * @return the sum
     */
    BigInteger getSum();
}
