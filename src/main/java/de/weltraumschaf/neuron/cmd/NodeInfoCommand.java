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
import de.weltraumschaf.neuron.Node;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;

/**
 * Executes `node info ID` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeInfoCommand extends BaseCommand {

    public NodeInfoCommand(final Environment env, final IO io) {
        this(env, io, DEFAULT_ARGUMETS);
    }

    public NodeInfoCommand(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        final Token<Integer> arg = getArguments().get(0);

        if (getEnv().hasNode(arg.getValue())) {
            final Node inspectedNode = getEnv().getNode(arg.getValue());
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

            getIo().println(info.toString());
        } else {
            getIo().println(String.format("Node with id %d does not exist!", arg.getValue()));
        }
    }

}
