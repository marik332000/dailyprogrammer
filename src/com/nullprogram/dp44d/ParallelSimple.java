package com.nullprogram.dp44d;

import java.math.BigInteger;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.java.Log;
import lombok.val;

/**
 * Runs a Simple solver on each CPU core.
 */
@Log
@ToString
@RequiredArgsConstructor
public class ParallelSimple implements Solver {

    /**
     * The number of threads to use.
     */
    private static final int NTHREADS =
        Runtime.getRuntime().availableProcessors();

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
        /* Figure out how much to split up the work. */
        BigInteger range = max.subtract(min);
        BigInteger nthreads = BigInteger.valueOf(NTHREADS);
        BigInteger subrange = range.divide(nthreads).add(BigInteger.ONE);
        val exec = Executors.newFixedThreadPool(NTHREADS);
        val service = new ExecutorCompletionService<Solver>(exec);

        /* Spin off a job for each. */
        BigInteger submin = min;
        int subcount = 0;
        while (submin.compareTo(max) < 0) {
            BigInteger submax = max.min(subrange.add(submin));
            Solver solver = new Simple(submin, submax);
            service.submit(solver, solver);
            log.fine(String.format("submitted job (%s, %s)", submin, submax));
            submin = submax;
            subcount++;
        }

        /* Retrieve completed entries. */
        try {
            for (int i = 0; i < subcount; i++) {
                Solver solver = service.take().get();
                log.fine("completed job");
                sum = sum.add(solver.getSum());
                count += solver.getCount();
            }
        } catch (Exception e) {
            log.warning(e.toString());
        }
        exec.shutdownNow();
    }
}
