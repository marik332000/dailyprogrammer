package com.nullprogram.dp46i;

public class Launcher {
    public static void main(String[] args) {
        int total = 1000000;
        int wins = 0;
        for (int i = 0; i < total; i++) {
            Player player = new RepeatPlayer();
            Game game = new Game(player);
            if (game.play()) {
                wins++;
            }
        }
        double percent = wins * 100.0 / total;
        System.out.println("Win %: " + percent);
    }

    private void human() {
        Player player = new ConsolePlayer();
        Game game = new Game(player);
        if (game.play()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lose!");
        }
    }
}
