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

        final MainType command = Command.getCommand(commandtoken);
        SubType subCommand = SubType.NONE;

        if (tokens.size() > 1) {
            final Token secondToken = tokens.get(1);

            if (secondToken.getType() == TokenType.STRING && Command.isSubCommand(secondToken)) {
                subCommand = Command.getSubCommand(secondToken);
            }
        }

        final List<Token> arguments = Lists.newArrayList();

        return new Command(command, subCommand, arguments);
    }

}
