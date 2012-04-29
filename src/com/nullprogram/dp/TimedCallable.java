package com.nullprogram.dp;

import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;

/**
 * Turns a runnable into a callable that returns its execution time.
 */
@RequiredArgsConstructor
public final class TimedCallable implements Callable<Double> {

    /**
     * The task to be executed.
     */
    private final Runnable task;

    @Override
    public Double call() {
        TimedExec exec = new TimedExec();
        return exec.execute(task);
    }
}
