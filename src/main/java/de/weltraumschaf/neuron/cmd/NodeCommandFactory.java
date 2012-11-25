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
import de.weltraumschaf.commons.shell.ShellCommand;
import de.weltraumschaf.commons.shell.Token;
import de.weltraumschaf.neuron.shell.Environment;
import java.util.List;

/**
 * Sub factory for node commands.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class NodeCommandFactory {

    /**
     * Shell environment.
     */
    private final Environment env;

    /**
     * Shell I/O.
     */
    private final IO io;

    /**
     * Dedicated constructor.
     *
     * @param env shell environment used by commands
     * @param io shell I/O used by commands
     */
    NodeCommandFactory(final Environment env, final IO io) {
        super();
        this.env = env;
        this.io = io;
    }

    /**
     * Helper to create sub commands for node command.
     *
     * @param subCommand used to determine appropriate command
     * @param arguments command line arguments
     * @return command object
     * // CHECKSTYLE:OFF
     * @throws IllegalArgumentException if, can't create command of bad sub command type
     * // CHECKSTYLE:ON
     */
    Command newNodeCommand(final ShellCommand.SubType subCommand, final List<Token> arguments) {
        switch (subCommand) {
            case ADD:
                return new NodeAdd(env, io, arguments);
            case CONNECT:
                return new NodeConnect(env, io, arguments);
            case DISCONNECT:
                return new NodeDisconnect(env, io, arguments);
            case DEL:
                return new NodeDel(env, io, arguments);
            case INFO:
                return new NodeInfo(env, io, arguments);
            case LIST:
                return new NodeList(env, io, arguments);
            case LISTEN:
                return new NodeListen(env, io, arguments);
            case UNLISTEN:
                return new NodeUnlisten(env, io, arguments);
            default:
                throw new IllegalArgumentException(
                            String.format("Main command type 'node' does not support sub type '%s'!", subCommand));
        }
    }

}
