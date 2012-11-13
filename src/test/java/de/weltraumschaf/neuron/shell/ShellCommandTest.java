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

import de.weltraumschaf.neuron.shell.ShellCommand.MainType;
import de.weltraumschaf.neuron.shell.ShellCommand.SubType;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 *
 * @author sxs
 */
public class ShellCommandTest {

    @Test
    public void isCommand() {
        assertThat(ShellCommand.isCommand(Token.newToken("help")), is(true));
        assertThat(ShellCommand.isCommand(Token.newToken("reset")), is(true));
        assertThat(ShellCommand.isCommand(Token.newToken("exit")), is(true));
        assertThat(ShellCommand.isCommand(Token.newToken("node")), is(true));

        assertThat(ShellCommand.isCommand(Token.newToken("foo")), is(false));
    }

    @Test
    public void getCommand() {
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

    @Test
    public void getSubCommand() {
        assertThat(ShellCommand.determineSubCommand(Token.newToken("add")), is(SubType.ADD));
        assertThat(ShellCommand.determineSubCommand(Token.newToken("del")), is(SubType.DEL));
        assertThat(ShellCommand.determineSubCommand(Token.newToken("connect")), is(SubType.CONNECT));
        assertThat(ShellCommand.determineSubCommand(Token.newToken("list")), is(SubType.LIST));
        assertThat(ShellCommand.determineSubCommand(Token.newToken("info")), is(SubType.INFO));
    }

}
