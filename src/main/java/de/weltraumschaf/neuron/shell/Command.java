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

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class Command {

    enum MainType {

        HELP("help"), RESET("reset"), EXIT("exit"), NODE("node");

        private final String name;

        private MainType(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    enum SubType {
        NONE(""), ADD("add"), DEL("del"), CONNECT("connect"), LIST("list"), INFO("info");

        private final String name;

        private SubType(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    private static final Map<String, MainType> COMMANDS = Maps.newHashMap();
    private static final Map<String, SubType> SUB_COMMANDS = Maps.newHashMap();

    static {
        for (final MainType t : MainType.values()) {
            COMMANDS.put(t.toString(), t);
        }
        for (final SubType t : SubType.values()) {
            if (t == SubType.NONE) {
                continue; // Ignore to do not recognize empty strings in #isSubCommand().
            }
            SUB_COMMANDS.put(t.toString(), t);
        }
    }

    private final MainType command;
    private final SubType subCommand;
    private final List<Token> arguments;

    public Command(final MainType mainCommand, final List<Token> arguments) {
        this(mainCommand, SubType.NONE, arguments);
    }

    public Command(final MainType command, final SubType subCommand, final List<Token> arguments) {
        this.command = command;
        this.subCommand = subCommand;
        this.arguments = Lists.newArrayList(arguments);
    }

    public MainType getCommand() {
        return command;
    }

    public SubType getSubCommand() {
        return subCommand;
    }

    public List<Token> getArguments() {
        return Lists.newArrayList(arguments);
    }

    static boolean isCommand(Token<String> t) {
        return COMMANDS.containsKey(t.getValue());
    }

    static MainType determineCommand(Token<String> t) {
        return COMMANDS.get(t.getValue());
    }

    static boolean isSubCommand(Token<String> t) {
        return SUB_COMMANDS.containsKey(t.getValue());
    }

    static SubType determineSubCommand(Token<String> t) {
        return SUB_COMMANDS.get(t.getValue());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add("mainCommand", command)
                      .add("subCommand", subCommand)
                      .add("arguments", arguments)
                      .toString();
    }

}
