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

    public CommandFactory(final Environment env, final IO io) {
        this.env = env;
        this.io = io;
    }

    public Command newCommand(final ShellCommand cmd) {
        switch (cmd.getCommand()) {
            case EXIT:
                return new ExitCommand(env, io, cmd.getArguments());
            case HELP:
                return new HelpCommand(env, io, cmd.getArguments());
            case NODE:
                return newNodeCommand(cmd.getSubCommand(), cmd.getArguments());
            case RESET:
                return new ResetCommand(env, io, cmd.getArguments());
            default:
                throw new IllegalArgumentException(String.format("Unsupported main command type '%s'!", cmd.getCommand()));
        }
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
                throw new IllegalArgumentException(String.format("Main command type NODE does not support sub type '%s'!", subCommand));
        }
    }
}
