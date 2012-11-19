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
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;

/**
 * Executes `node connect FROM TO` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeConnect extends BaseCommand {

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public NodeConnect(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        final Token<Integer> sourceNode = getArguments().get(0);
        final Token<Integer> destinationNodeId = getArguments().get(1);

        if (! getEnv().hasNode(sourceNode.getValue())) {
            getIo().println(String.format("Node with id %d does not exist!", sourceNode.getValue()));
            return;
        }

        if (! getEnv().hasNode(destinationNodeId.getValue())) {
            getIo().println(String.format("Node with id %d does not exist!", destinationNodeId.getValue()));
            return;
        }

        final Node source = getEnv().getNode(sourceNode.getValue());
        final Node destination = getEnv().getNode(destinationNodeId.getValue());
        getIo().println(String.format("Conect nodes: %d -> %d.",
                                      sourceNode.getValue(),
                                      destinationNodeId.getValue()));
        source.connect(destination);
    }

}
