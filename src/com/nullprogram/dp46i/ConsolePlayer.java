package com.nullprogram.dp46i;

import java.io.Console;

/**
 * Allows a human to play from the console.
 */
public class ConsolePlayer implements Player {

    private final State state = new State();

    private Console console = System.console();

    @Override
    public int place(int n) {
        console.writer().println(state);
        int pos = -1;
        do {
            console.writer().print("(" + n + ") > ");
            console.writer().flush();
            try {
                pos = new Integer(console.readLine()).intValue();
            } catch (NumberFormatException e) {
                continue;
            }
        } while (state.filled(pos));
        state.set(pos, n);
        return pos;
    }
}
