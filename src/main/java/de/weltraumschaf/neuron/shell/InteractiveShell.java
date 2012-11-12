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
import de.weltraumschaf.neuron.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class InteractiveShell {

    private static final String HELP =
          "This is the Neuron Interactive shell version %s.%n%n"
        + "Available commands:%n"
        + "  help                           Show all available commands.%n"
        + "  reset                          Reset the whole environment.%n"
        + "  exit                           Exit the interactive shell.%n"
        + "  node add [AMOUNT]              Creates one new node or AMOUNT nodes.%n"
        + "  node del ID                    Disconnect and deletes the node with ID.%n"
        + "  node connect ID NEIGHBOR_ID    Connect two nodes.%n"
        + "  node list                      List all nodes.%n"
        + "  node info ID                   Print info of a node.%n"
        + "  message FROM_ID TO_ID MESSAGE  Send a message from one node to other.%n%n";
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

    private void executeNode(final Command cmd) {
        switch (cmd.getSubCommand()) {
            case ADD:
                addNode(cmd);
                break;
            case LIST:
                listNodes();
                break;
            case INFO:
                nodeInfo(cmd);
                break;
            case DEL:
                deleteNode(cmd);
                break;
            case CONNECT:
                connectNodes(cmd);
                break;
            default:
                io.println("Unknown subcommand: " + cmd.getSubCommand());
        }
    }

    private void addNode(final Command cmd) {
        int amount = 1;

        if (cmd.getArguments().size() == 1) {
            final Token<Integer> t = cmd.getArguments().get(0);
            amount = t.getValue();
        }

        if (amount < 1) {
            io.println("Parameter AMOUNT must not be less than 1!");
            return;
        }

        final StringBuilder summary = new StringBuilder();

        for (int i = 0; i < amount; ++i) {
            final Node n = env.add();
            summary.append(String.format("Node with id %d added%n", n.getId()));
        }

        io.println(summary.toString());
    }

    private void listNodes() {
        final StringBuilder summary = new StringBuilder();
        final List<Node> nodes = env.getNodes();

        if (nodes.isEmpty()) {
            summary.append(String.format("No nodes created.%n"));
        } else {
            summary.append(String.format("%d nodes created.%n%n", nodes.size()));
            summary.append(String.format("Existing nodes:%n"));
            for (final Node n : nodes) {
                summary.append(String.format("  %s%n", n));
            }
        }

        io.println(summary.toString());
    }

    private void nodeInfo(final Command cmd) {
        final Token<Integer> arg = cmd.getArguments().get(0);

        if (env.hasNode(arg.getValue())) {
            final Node inspectedNode = env.getNode(arg.getValue());
            final StringBuilder info = new StringBuilder();
            info.append(String.format("%s%n", inspectedNode.toString()));
            info.append(String.format("Neighbors:%n"));

            if (inspectedNode.hasNeighbors()) {
                for (final Node neighbor : inspectedNode.getNeighbors()) {
                    info.append(String.format("  %s%n", neighbor));
                }
            } else {
                info.append(String.format("  Has no neighbors!%n"));
            }
            io.println(info.toString());
        } else {
            io.println(String.format("Node with id %d does not exist!", arg.getValue()));
        }
    }

    private void deleteNode(final Command cmd) {
        final Token<Integer> argId = cmd.getArguments().get(0);

        if ( ! env.hasNode(argId.getValue())) {
            io.println(String.format("Node with id %d does not exist!", argId.getValue()));
            return;
        }

        env.remove(argId.getValue());
    }

    private void connectNodes(final Command cmd) {
        final Token<Integer> argId = cmd.getArguments().get(0);
        final Token<Integer> argNeighborId = cmd.getArguments().get(1);

        if ( ! env.hasNode(argId.getValue())) {
            io.println(String.format("Node with id %d does not exist!", argId.getValue()));
            return;
        }

        if ( ! env.hasNode(argNeighborId.getValue())) {
            io.println(String.format("Node with id %d does not exist!", argNeighborId.getValue()));
            return;
        }

        final Node connector = env.getNode(argId.getValue());
        final Node connected = env.getNode(argNeighborId.getValue());
        connector.connect(connected);
        io.println(String.format("Conected nodes: %d -> %d.", argId.getValue(), argNeighborId.getValue()));
    }

}
