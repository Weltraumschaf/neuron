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

/**
 * Factory to create command objects depending on the parsed {@link ShellCommand}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
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
     * Sub factory for node commands.
     */
    private final NodeCommandFactory nodeCommandFactory;

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
        nodeCommandFactory = new NodeCommandFactory(this.env, this.io);
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
                cmd = nodeCommandFactory.newNodeCommand(shellCmd.getSubCommand(), shellCmd.getArguments());
                break;
            case RESET:
                cmd = new Reset(env, io, shellCmd.getArguments());
                break;
            case MESSAGE:
                cmd = new SendMessage(env, io, shellCmd.getArguments());
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

}
