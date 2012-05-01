package com.nullprogram.dp46i;

import java.util.Random;
import lombok.RequiredArgsConstructor;

/**
 * A one-shot instance of a game.
 */
@RequiredArgsConstructor
public final class Game {

    private static final Random RNG = new Random();

    private final Player player;
    private final State state = new State();

    /**
     * Play this game through.
     * @return true for a win
     */
    public boolean play() {
        for (int i = 0; i < State.NSLOTS; i++) {
            int value = RNG.nextInt(State.NVALUES);
            int index = player.place(value);
            if (index < 0) {
                return false; // forfeit
            } else if (!state.set(index, value)) {
                return false;
            }
        }
        return state.won();
    }
}
