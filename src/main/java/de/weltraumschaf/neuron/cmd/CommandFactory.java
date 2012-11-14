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
import de.weltraumschaf.commons.Version;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.ShellCommand;
import de.weltraumschaf.neuron.shell.ShellCommand.SubType;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;

/**
 * Factory to create command objects depending on the parsed {@link ShellCommand}.
 *
 * @author @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class CommandFactory {

    /**
     * Shell environment.
     */
    private final Environment env;

    /**
     * Shell I/O.
     */
    private final IO io;

    /**
     * Version of the shell.
     */
    private final Version version;

    /**
     * Dedicated constructor.
     *
     * @param env shell environment used by commands
     * @param io shell I/O used by commands
     * @param version version info used by some commands
     */
    public CommandFactory(final Environment env, final IO io, final Version version) {
        super();
        this.env = env;
        this.io = io;
        this.version = version;
    }

    /**
     * Create command instances according to the parsed shell command.
     *
     * @param shellCmd used to determine appropriate command
     * @return command object
     * // CHECKSTYLE:OFF
     * @throws IllegalArgumentException if, can't create command of bad main or sub command type
     * // CHECKSTYLE:ON
     */
    public Command newCommand(final ShellCommand shellCmd) {
        Command cmd;
        switch (shellCmd.getCommand()) {
            case EXIT:
                cmd = new Exit(env, io, shellCmd.getArguments());
                break;
            case HELP:
                cmd = new Help(env, io, shellCmd.getArguments());
                break;
            case NODE:
                cmd = newNodeCommand(shellCmd.getSubCommand(), shellCmd.getArguments());
                break;
            case RESET:
                cmd = new Reset(env, io, shellCmd.getArguments());
                break;
            default:
                throw new IllegalArgumentException(String.format("Unsupported main command type '%s'!",
                                                                 shellCmd.getCommand()));
        }

        if (cmd instanceof UseVersion) {
            ((UseVersion) cmd).setVersion(version);
        }

        return cmd;
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
    private Command newNodeCommand(final SubType subCommand, final List<Token> arguments) {
        switch (subCommand) {
            case ADD:
                return new NodeAdd(env, io, arguments);
            case CONNECT:
                return new NodeConnect(env, io, arguments);
            case DEL:
                return new NodeDel(env, io, arguments);
            case INFO:
                return new NodeInfo(env, io, arguments);
            case LIST:
                return new NodeList(env, io, arguments);
            default:
                throw new IllegalArgumentException(
                            String.format("Main command type NODE does not support sub type '%s'!", subCommand));
        }
    }
}
