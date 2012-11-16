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
import de.weltraumschaf.neuron.shell.ShellCommand.MainType;
import de.weltraumschaf.neuron.shell.ShellCommand.SubType;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author sxs
 */
public class ShellCommandTest {

    @Test
    public void constructCommandWithoutSubCommand() {
        final List<Token> args = Lists.newArrayList();
        final ShellCommand sut = new ShellCommand(MainType.HELP, args);
        assertThat(sut.getCommand(), is(MainType.HELP));
        assertThat(sut.getSubCommand(), is(SubType.NONE));
        assertThat(sut.getArguments(), is(args));
    }

    @Test
    public void constructCommandWithSubCommand() {
        final List<Token> args = Lists.newArrayList();
        final ShellCommand sut = new ShellCommand(MainType.NODE, SubType.ADD, args);
        assertThat(sut.getCommand(), is(MainType.NODE));
        assertThat(sut.getSubCommand(), is(SubType.ADD));
        assertThat(sut.getArguments(), is(args));
    }

    @Test
    public void isCommand() {
        assertThat(ShellCommand.isCommand(Token.newToken("help")), is(true));
        assertThat(ShellCommand.isCommand(Token.newToken("reset")), is(true));
        assertThat(ShellCommand.isCommand(Token.newToken("exit")), is(true));
        assertThat(ShellCommand.isCommand(Token.newToken("node")), is(true));

        assertThat(ShellCommand.isCommand(Token.newToken("foo")), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void determineBadCommand() {
        ShellCommand.determineCommand(Token.newToken("foo"));
    }

    @Test
    public void determineCommand() {
        assertThat(ShellCommand.determineCommand(Token.newToken("help")), is(MainType.HELP));
        assertThat(ShellCommand.determineCommand(Token.newToken("reset")), is(MainType.RESET));
        assertThat(ShellCommand.determineCommand(Token.newToken("exit")), is(MainType.EXIT));
        assertThat(ShellCommand.determineCommand(Token.newToken("node")), is(MainType.NODE));
    }

    @Test
    public void isSubCommand() {
        assertThat(ShellCommand.isSubCommand(Token.newToken("add")), is(true));
        assertThat(ShellCommand.isSubCommand(Token.newToken("del")), is(true));
        assertThat(ShellCommand.isSubCommand(Token.newToken("connect")), is(true));
        assertThat(ShellCommand.isSubCommand(Token.newToken("list")), is(true));
        assertThat(ShellCommand.isSubCommand(Token.newToken("info")), is(true));

        assertThat(ShellCommand.isCommand(Token.newToken("foo")), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void determineBadSubCommand() {
        ShellCommand.determineSubCommand(Token.newToken("foo"));
    }

    @Test
    public void determineSubCommand() {
        assertThat(ShellCommand.determineSubCommand(Token.newToken("add")), is(SubType.ADD));
        assertThat(ShellCommand.determineSubCommand(Token.newToken("del")), is(SubType.DEL));
        assertThat(ShellCommand.determineSubCommand(Token.newToken("connect")), is(SubType.CONNECT));
        assertThat(ShellCommand.determineSubCommand(Token.newToken("list")), is(SubType.LIST));
        assertThat(ShellCommand.determineSubCommand(Token.newToken("info")), is(SubType.INFO));
    }

    @Test
    public void testToString() {
        final List<Token> args = Lists.newArrayList();
        args.add(Token.newToken("foo"));
        args.add(Token.newToken(23));

        ShellCommand sut = new ShellCommand(MainType.HELP, args);
        assertThat(sut.toString(), is("ShellCommand{mainCommand=help, subCommand=, arguments=[Token{type=LITERAL, "
                + "value=foo}, Token{type=INTEGER, value=23}]}"));
        sut = new ShellCommand(MainType.NODE, SubType.ADD, args);
        assertThat(sut.toString(), is("ShellCommand{mainCommand=node, subCommand=add, arguments=[Token{type=LITERAL, "
                + "value=foo}, Token{type=INTEGER, value=23}]}"));
    }

}