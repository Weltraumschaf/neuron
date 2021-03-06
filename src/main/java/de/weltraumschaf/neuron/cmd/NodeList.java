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
package de.weltraumschaf.neuron.cmd;

import de.weltraumschaf.commons.IO;
import de.weltraumschaf.commons.shell.Token;
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.shell.Environment;
import java.util.List;

/**
 * Executes `node list` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeList extends BaseCommand {

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public NodeList(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        final StringBuilder summary = new StringBuilder();
        final List<Node> nodes = getEnv().getNodes();

        if (nodes.isEmpty()) {
            summary.append(String.format("No nodes created.%n"));
        } else {
            if (nodes.size() == 1) {
                summary.append(String.format("%d node created.%n%n", nodes.size()));
            } else {
                summary.append(String.format("%d nodes created.%n%n", nodes.size()));
            }

            summary.append(String.format("Existing nodes:%n"));
            for (final Node n : nodes) {
                summary.append(String.format("  %s%n", n));
            }
        }

        getIo().println(summary.toString());
    }

}
