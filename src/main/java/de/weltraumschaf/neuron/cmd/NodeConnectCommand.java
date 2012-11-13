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
 * Executes `node connect FROM TO` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeConnectCommand extends BaseCommand {

    /**
     * Constructor for no argument command.
     *
     * @param env shell environment
     * @param io shell I/O
     */
    public NodeConnectCommand(final Environment env, final IO io) {
        this(env, io, DEFAULT_ARGUMETS);
    }

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public NodeConnectCommand(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        final Token<Integer> argId = getArguments().get(0);
        final Token<Integer> argNeighborId = getArguments().get(1);

        if (! getEnv().hasNode(argId.getValue())) {
            getIo().println(String.format("Node with id %d does not exist!", argId.getValue()));
            return;
        }

        if (! getEnv().hasNode(argNeighborId.getValue())) {
            getIo().println(String.format("Node with id %d does not exist!", argNeighborId.getValue()));
            return;
        }

        final Node connector = getEnv().getNode(argId.getValue());
        final Node connected = getEnv().getNode(argNeighborId.getValue());
        connector.connect(connected);
        getIo().println(String.format("Conected nodes: %d -> %d.", argId.getValue(), argNeighborId.getValue()));
    }

}
