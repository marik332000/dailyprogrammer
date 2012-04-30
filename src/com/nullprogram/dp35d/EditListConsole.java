package com.nullprogram.dp35d;

import java.io.Console;
import java.io.PrintWriter;

/**
 * A command-line interface to an EditList. This replicates the
 * challenge example.
 */
public final class EditListConsole implements Runnable {

    /**
     * The main prompt message.
     */
    private static final String PROMPT =
        "Enter command ('A'dd, 'E'dit, 'D'elete, 'U'ndo, 'R'edo): ";

    /**
     * The EditList being worked on.
     */
    private EditList list = new EditList();

    /**
     * The console to operate through.
     */
    private Console console = System.console();

    /**
     * The console output writer.
     */
    private PrintWriter out = console.writer();

    /**
     * Prompt the user.
     * @param text  the prompt text
     */
    private void prompt(final String text) {
        out.print(text);
        out.flush();
    }

    @Override
    public void run() {
        while (true) {
            for (String item : list.getList()) {
                out.println(item);
            }
            prompt(PROMPT);
            String input = console.readLine();
            if (input == null) {
                out.println();
                return;
            } else if (input.length() == 0) {
                continue;
            }
            char select = input.toLowerCase().charAt(0);
            int index;
            try {
                switch (select) {
                case 'a':
                    prompt("Enter text to add: ");
                    list = list.add(console.readLine());
                    break;
                case 'e':
                    prompt("Enter index to edit: ");
                    index = new Integer(console.readLine());
                    prompt("Enter text to edit: ");
                    list = list.edit(index, console.readLine());
                    break;
                case 'd':
                    prompt("Enter index to delete: ");
                    index = new Integer(console.readLine());
                    list = list.delete(index);
                    break;
                case 'u':
                    list = list.undo();
                    break;
                case 'r':
                    list = list.redo();
                    break;
                default:
                    out.println("Unknown command: " + select);
                    break;
                }
            } catch (Exception e) {
                out.println("error: " + e.getMessage());
            }
        }
    }
}
