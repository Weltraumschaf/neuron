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
import de.weltraumschaf.neuron.shell.NeuronSubType;
import de.weltraumschaf.commons.shell.Token;
import de.weltraumschaf.neuron.shell.Environment;
import java.util.List;

/**
 * Sub factory for dump commands.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class DumpCommandFactory {

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
    DumpCommandFactory(final Environment env, final IO io) {
        super();
        this.env = env;
        this.io = io;
    }

    /**
     * Helper to create sub commands for dump command.
     *
     * @param subCommand used to determine appropriate command
     * @param arguments command line arguments
     * @return command object
     * // CHECKSTYLE:OFF
     * @throws IllegalArgumentException if, can't create command of bad sub command type
     * // CHECKSTYLE:ON
     */
    Command newDumpCommand(final NeuronSubType subCommand, final List<Token> arguments) {
        switch (subCommand) {
            case DOT:
                return new DumpDot(env, io, arguments);
            default:
                throw new IllegalArgumentException(
                            String.format("Main command type 'dump' does not support sub type '%s'!", subCommand));
        }
    }
}
