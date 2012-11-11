/*
 *  LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
package de.weltraumschaf.neuron;

import de.weltraumschaf.commons.IO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class InteractiveShell {

    private final IO io;
    private final Environment env;

    public InteractiveShell(final IO io) {
        this.io = io;
        env = new Environment();
    }

    void start() throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(io.getStdin()));
        final PrintStream out = io.getStdout();

        while (true) {
            final String inputLine = input.readLine();

            if ("exit".equals(inputLine)) {
                out.println("bye bye!");
                break;
            } else if ("reset".equals(inputLine)) {

            } else if ("help".equals(inputLine)) {

            } else {
                out.println("Unknown comand: " + inputLine);
            }
        }
    }

    void exit() {

    }
}
