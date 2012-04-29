package com.nullprogram.dp;

import java.util.concurrent.Callable;
import lombok.Getter;
import lombok.ToString;

/**
 * Used to conveniently measure the running time of runnables and
 * callables. It keeps track of the overall elapsted time for all
 * submitted tasks.
 */
@ToString
public final class TimedExec {

    /**
     * Nanoseconds in a second.
     */
    private static final double NANO = 1000000000.0;

    /**
     * Overall elapsed running time for this instance.
     */
    @Getter
    private double elapsed = 0;

    /**
     * Execute the callable and add the running time to the total.
     * @param task  the task to execute
     * @param <V>   the type of the callable's return
     * @return the callable's return value
     */
    public <V> V submit(final Callable<V> task) {
        try {
            long start = System.nanoTime();
            V result =  task.call();
            elapsed += (System.nanoTime() - start) / NANO;
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute the runnable and add it's running time to the total.
     * @param task  the task to execute
     * @return the running time of this task
     */
    public double execute(final Runnable task) {
        long start = System.nanoTime();
        task.run();
        double time = (System.nanoTime() - start) / NANO;
        elapsed += time;
        return time;
    }
}
