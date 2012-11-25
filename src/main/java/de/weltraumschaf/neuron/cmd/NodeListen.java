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
import de.weltraumschaf.commons.shell.TokenType;
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.shell.Environment;
import java.util.List;

/**
 * Executes `node unlisten ID`.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeListen extends BaseCommand {

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public NodeListen(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        if (getArguments().get(0).getType() == TokenType.NUMBER) {
            final Token<Integer> t = getArguments().get(0);
            listneToSingleNode(t.getValue());
        } else if (getArguments().get(0).getType() == TokenType.LITERAL) {
            final Token<String> t = getArguments().get(0);
            if ("all".equals(t.getValue())) {
                listenToAllNodes();
            } else {
                throw new IllegalArgumentException(String.format("Unrecognizable argument '%s'!",
                                                                 t.getValue()));
            }
        } else {
            throw new IllegalArgumentException(String.format("Unrecognizable argument '%s'!",
                                                             getArguments().get(0).getValue()));
        }
    }

    /**
     * Add environments event handler to all nodes.
     */
    private void listenToAllNodes() {
        getIo().println("Listening for events emmitted by all nodes.");
        for (final Node n : getEnv().getNodes()) {
            n.addObserver(getEnv().getHandler());
        }
    }

    /**
     * Add environments event handler to a specific node, if it exists.
     *
     * @param id the node id
     */
    private void listneToSingleNode(final int id) {
        if (getEnv().hasNode(id)) {
            getIo().println(String.format("Listening for events emmitted by node '%d'.", id));
            final Node n = getEnv().getNode(id);
            n.addObserver(getEnv().getHandler());
        } else {
            getIo().println(String.format("There is no node with id '%d'!", id));
        }
    }

}
