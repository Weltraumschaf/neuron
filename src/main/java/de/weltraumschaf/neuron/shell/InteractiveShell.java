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

import de.weltraumschaf.commons.IO;
import de.weltraumschaf.commons.Version;
import de.weltraumschaf.neuron.Environment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class InteractiveShell {

    private static final String HELP =
          "This is the Neuron Interactive shell version %s.%n%n"
        + "Available commands:%n"
        + "  help                        Show all available commands.%n"
        + "  reset                       Reset the whole environment.%n"
        + "  exit                        Exit the interactive shell.%n"
        + "  node add [AMOUNT]           Creates one new node or AMOUNT nodes.%n"
        + "  node del ID                 Disconnect and deletes the node with ID.%n"
        + "  node connect FROM_ID TO_ID  Connect two nodes.%n"
        + "  node list                   List all nodes.%n"
        + "  node info ID                Print info of a node.%n";
    private final IO io;
    private final Environment env;
    private final Parser parser = new Parser(new Scanner());
    private final Version version;
    private boolean run = true;

    public InteractiveShell(final IO io) throws IOException {
        this.io = io;
        env = new Environment();
        version = new Version("/de/weltraumschaf/neuron/version.properties");
        version.load();
    }

    public void start() throws IOException {
        final BufferedReader input = new BufferedReader(new InputStreamReader(io.getStdin()));
        io.println(String.format("Welcome to Neuro Interactive Shell!%n"));

        while (run) {
            io.print("> ");
            final String inputLine = input.readLine();
            try {
                final Command cmd = parser.parse(inputLine);
                execute(cmd);
            } catch (SyntaxException ex) {
                io.println("Error: " + ex.getMessage());
            }
        }

        input.close();
    }

    public void exit() {
        run = false;
    }

    private void execute(final Command cmd) {
        switch (cmd.getCommand()) {
            case EXIT:
                exit();
                io.println("bye bye!");
                break;
            case HELP:
                echoHelp();
                break;
            case RESET:
                io.println("Resetting...");
                env.reset();
                break;
            case NODE:
                executeNode(cmd);
                break;
            default:
                io.println("Unknown comand: " + cmd);
                break;
        }
    }

    private void echoHelp() {
        io.print(String.format(HELP, version.getVersion()));
    }

    private void executeNode(Command cmd) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
