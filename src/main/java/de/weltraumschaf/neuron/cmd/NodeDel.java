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
import de.weltraumschaf.neuron.shell.Environment;
import java.util.List;

/**
 * Executes `node del ID` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeDel extends BaseCommand {

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public NodeDel(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        final Token<Integer> argId = getArguments().get(0);

        if (! getEnv().hasNode(argId.getValue())) {
            getIo().println(String.format("Node with id %d does not exist!", argId.getValue()));
            return;
        }


        getEnv().remove(argId.getValue());
    }

}
