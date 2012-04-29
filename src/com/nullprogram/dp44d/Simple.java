package com.nullprogram.dp44d;

import java.math.BigInteger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import static java.math.BigInteger.ONE;

/**
 * This solver cheats a bit by taking great advantage of BigInteger's
 * probabilistic prime tester method, which uses both Miller-Rabin and
 * Lucas-Lehmer.
 */
@ToString
@RequiredArgsConstructor
public class Simple implements Solver {

    /**
     * The accuracy when testing for primes (2^-ACCURACY).
     */
    private static final int ACCURACY = 10;

    /**
     * The minimum of the range, inclusive.
     */
    private final BigInteger min;

    /**
     * The maximum of the range, exclusive.
     */
    private final BigInteger max;

    /**
     * The total sum of primes in range.
     */
    @Getter
    private BigInteger sum = BigInteger.ZERO;

    /**
     * The number of primes in range.
     */
    @Getter
    private int count = 0;

    @Override
    public final void run() {
        for (BigInteger i = min; i.compareTo(max) < 0; i = i.add(ONE)) {
            if (i.isProbablePrime(ACCURACY)) {
                sum = sum.add(i);
                count++;
            }
        }
    }
}
