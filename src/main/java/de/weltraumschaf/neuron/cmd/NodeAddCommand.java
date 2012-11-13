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
 * Executes `node add` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeAddCommand extends BaseCommand {

    /**
     * Constructor for no argument command.
     *
     * @param env shell environment
     * @param io shell I/O
     */
    public NodeAddCommand(final Environment env, final IO io) {
        this(env, io, DEFAULT_ARGUMETS);
    }

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public NodeAddCommand(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        int amount = 1;

        if (getArguments().size() == 1) {
            final Token<Integer> t = getArguments().get(0);
            amount = t.getValue();
        }

        if (amount < 1) {
            getIo().println("Parameter AMOUNT must not be less than 1!");
            return;
        }

        final StringBuilder summary = new StringBuilder();

        for (int i = 0; i < amount; ++i) {
            final Node n = getEnv().add();
            summary.append(String.format("Node with id %d added%n", n.getId()));
        }

        getIo().println(summary.toString());
    }

}
