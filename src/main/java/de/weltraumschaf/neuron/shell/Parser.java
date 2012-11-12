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
package de.weltraumschaf.neuron.shell;

import com.google.common.collect.Lists;
import de.weltraumschaf.neuron.shell.Command.MainType;
import de.weltraumschaf.neuron.shell.Command.SubType;
import java.util.List;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class Parser {

    private final Scanner scanner;

    public Parser(final Scanner scanner) {
        this.scanner = scanner;
    }

    Command parse(final String input) throws SyntaxException {
        final List<Token> tokens = scanner.scan(input);
        final Token commandtoken = tokens.get(0);

        if (TokenType.STRING != commandtoken.getType()) {
            throw new SyntaxException("Command expected as first word!");
        }

        if ( ! Command.isCommand(commandtoken)) {
            throw new SyntaxException("Command expected as first word!");
        }

        final MainType command = Command.determineCommand(commandtoken);
        SubType subCommand = SubType.NONE;
        int argumentBegin = 1;

        if (tokens.size() > 1) {
            final Token secondToken = tokens.get(1);

            if (secondToken.getType() == TokenType.STRING && Command.isSubCommand(secondToken)) {
                ++argumentBegin;
                subCommand = Command.determineSubCommand(secondToken);
            }
        }

        List<Token> arguments;

        if (tokens.size() > argumentBegin) {
            arguments = tokens.subList(argumentBegin, tokens.size());
        } else {
            arguments = Lists.newArrayList();
        }

        final Command cmd = new Command(command, subCommand, arguments);
        verifyCommand(cmd);
        return cmd;
    }

    private void verifyCommand(final Command cmd) throws SyntaxException {
        switch (cmd.getCommand()) {
            case EXIT:
            case HELP:
            case RESET:
                if ( cmd.getSubCommand() != SubType.NONE) {
                    throw new SyntaxException(String.format("Command %s does not support subcommands!", cmd.getCommand()));
                }
                if ( ! cmd.getArguments().isEmpty()) {
                    throw new SyntaxException(String.format("Command %s does not support arguments!", cmd.getCommand()));
                }
                break;
            case NODE:{
                verifyNodeCommand(cmd);
            }
        }
    }

    private void verifyNodeCommand(final Command cmd) throws SyntaxException {
        final int argumentCount = cmd.getArguments().size();
        switch (cmd.getSubCommand()) {
            case LIST:
                if (argumentCount != 0) {
                    throw new SyntaxException(String.format("Command %s does support no arguments!", cmd.getCommand()));
                }
                break;
            case ADD:
                if (argumentCount != 0 && argumentCount != 1) {
                    throw new SyntaxException(String.format("Command %s one or zero arguments!", cmd.getCommand()));
                }
                break;
            case DEL:
            case INFO:
                if (argumentCount != 1) {
                    throw new SyntaxException(String.format("Command %s require one argument!", cmd.getCommand()));
                }
                break;
            case CONNECT:
                if (argumentCount != 2) {
                    throw new SyntaxException(String.format("Command %s require two argument!", cmd.getCommand()));
                }
                break;
            default:
                throw new SyntaxException(String.format("Command %s does not support subcommand %s!",
                                                        cmd.getCommand(),
                                                        cmd.getSubCommand()));

        }

    }

}
