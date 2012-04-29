package com.nullprogram.dp44d;

import java.math.BigInteger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This solver cheats a bit by taking great advantage of BigInteger's
 * probabilistic prime tester method, which uses both Miller-Rabin and
 * Lucas-Lehmer.
 */
@ToString
@RequiredArgsConstructor
public class Simple implements Solver {

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
    private BigInteger count = BigInteger.ZERO;

    @Override
    public final void run() {
        BigInteger i = min.subtract(BigInteger.ONE).nextProbablePrime();
        while (i.compareTo(max) < 0) {
            sum = sum.add(i);
            count = count.add(BigInteger.ONE);
            i = i.nextProbablePrime();
        }
    }
}
