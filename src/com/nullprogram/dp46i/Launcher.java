package com.nullprogram.dp46i;

/**
 * The main class.
 */
public final class Launcher {

    private static final int TOTAL = 1000000;

    /**
     * Hidden constructor.
     */
    private Launcher() {
    }

    /**
     * The main method.
     * @param args  command line arguments
     */
    public static void main(final String[] args) {
        int wins = 0;
        for (int i = 0; i < total; i++) {
            Player player = new RyaPlayer();
            Game game = new Game(player);
            if (game.play()) {
                wins++;
            }
        }
        double percent = wins * 100.0 / TOTAL;
        System.out.println("Win %: " + percent);
    }

    /**
     * Run an interactive game (for testing).
     */
    private void interactive() {
        Player player = new ConsolePlayer();
        Game game = new Game(player);
        if (game.play()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lose!");
        }
    }
}
