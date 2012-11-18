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
package de.weltraumschaf.neuron.shell;

import de.weltraumschaf.neuron.event.EventHandler;
import de.weltraumschaf.commons.IO;
import de.weltraumschaf.commons.Version;
import de.weltraumschaf.neuron.cmd.Command;
import de.weltraumschaf.neuron.cmd.CommandFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implements a simple REPL shell.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class InteractiveShell {

    /**
     * ShellCommand line I/O.
     */
    private final IO io;

    /**
     * Shell user input parser.
     */
    private final Parser parser = new Parser(new Scanner());

    /**
     * Factory to create commands.
     */
    private final CommandFactory factory;

    /**
     * Indicates if the REPL loop is running.
     */
    private boolean run = true;

    /**
     * Default constructor.
     *
     * @param io injection point for I/o
     * @throws IOException if, version properties could not be loaded for {@link Version}
     */
    public InteractiveShell(final IO io) throws IOException {
        super();
        this.io = io;
        final Version version = new Version("/de/weltraumschaf/neuron/version.properties");
        version.load();
        factory = new CommandFactory(new Environment(new EventHandler(this.io)), this.io, version);
    }

    /**
     * Start the REPL.
     *
     * @throws IOException if I/O error occurs
     */
    public void start() throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(io.getStdin()));
        io.println(String.format("Welcome to Neuro Interactive Shell!%n"));

        while (run) {
            io.print("> ");
            final String inputLine = input.readLine();
            try {
                final ShellCommand cmd = parser.parse(inputLine);
                execute(cmd);
            } catch (SyntaxException ex) {
                io.println("Error: " + ex.getMessage());
            }
        }

        input.close();
    }

    /**
     * Stops the REPL.
     */
    public void exit() {
        run = false;
    }

    /**
     * Executes a parsed command.
     *
     * @param shellCmd command to execute
     */
    private void execute(final ShellCommand shellCmd) {
        final Command cmd = factory.newCommand(shellCmd);
        cmd.execute();

        if (shellCmd.getCommand() == ShellCommand.MainType.EXIT) {
            exit();
        }
    }

}
