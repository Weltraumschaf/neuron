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

    public CommandFactory(final Environment env, final IO io, final Version version) {
        this.env = env;
        this.io = io;
        this.version = version;
    }

    public Command newCommand(final ShellCommand shellCmd) {
        Command cmd;
        switch (shellCmd.getCommand()) {
            case EXIT:
                cmd = new ExitCommand(env, io, shellCmd.getArguments());
                break;
            case HELP:
                cmd = new HelpCommand(env, io, shellCmd.getArguments());
                break;
            case NODE:
                cmd = newNodeCommand(shellCmd.getSubCommand(), shellCmd.getArguments());
                break;
            case RESET:
                cmd = new ResetCommand(env, io, shellCmd.getArguments());
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

    private Command newNodeCommand(final SubType subCommand, final List<Token> arguments) {
        switch (subCommand) {
            case ADD:
                return new NodeAddCommand(env, io, arguments);
            case CONNECT:
                return new NodeConnectCommand(env, io, arguments);
            case DEL:
                return new NodeDelCommand(env, io, arguments);
            case INFO:
                return new NodeInfoCommand(env, io, arguments);
            case LIST:
                return new NodeListCommand(env, io, arguments);
            default:
                throw new IllegalArgumentException(String.format("Main command type NODE does not support sub type '%s'!",
                                                                 subCommand));
        }
    }
}
