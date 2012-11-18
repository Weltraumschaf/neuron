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
import de.weltraumschaf.neuron.node.Message;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;

/**
 * Executes `message FROM TO 'MESSAGE'` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class SendMessage extends BaseCommand {

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public SendMessage(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        final Token<Integer> from = getArguments().get(0);
        final Token<Integer> to = getArguments().get(1);
        final Token<String> text = getArguments().get(2);
        final Message msg = new Message(text.getValue(), from.getValue(), to.getValue());

        if (! getEnv().hasNode(msg.getFrom())) {
            getIo().println(String.format("Node with id '%d' to send from does not exist!", msg.getFrom()));
            return;
        }

        getEnv().getNode(msg.getFrom()).send(msg);
        getIo().println(String.format("Send message %d -> %d.", from.getValue(), to.getValue()));
    }

}
