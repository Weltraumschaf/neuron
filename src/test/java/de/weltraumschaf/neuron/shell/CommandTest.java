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

import de.weltraumschaf.neuron.shell.Command.MainType;
import de.weltraumschaf.neuron.shell.Command.SubType;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 *
 * @author sxs
 */
public class CommandTest {

    @Test
    public void isCommand() {
        assertThat(Command.isCommand(Token.newToken("help")), is(true));
        assertThat(Command.isCommand(Token.newToken("reset")), is(true));
        assertThat(Command.isCommand(Token.newToken("exit")), is(true));
        assertThat(Command.isCommand(Token.newToken("node")), is(true));

        assertThat(Command.isCommand(Token.newToken("foo")), is(false));
    }

    @Test
    public void getCommand() {
        assertThat(Command.getCommand(Token.newToken("help")), is(MainType.HELP));
        assertThat(Command.getCommand(Token.newToken("reset")), is(MainType.RESET));
        assertThat(Command.getCommand(Token.newToken("exit")), is(MainType.EXIT));
        assertThat(Command.getCommand(Token.newToken("node")), is(MainType.NODE));
    }

    @Test
    public void isSubCommand() {
        assertThat(Command.isSubCommand(Token.newToken("add")), is(true));
        assertThat(Command.isSubCommand(Token.newToken("del")), is(true));
        assertThat(Command.isSubCommand(Token.newToken("connect")), is(true));
        assertThat(Command.isSubCommand(Token.newToken("list")), is(true));
        assertThat(Command.isSubCommand(Token.newToken("info")), is(true));

        assertThat(Command.isCommand(Token.newToken("foo")), is(false));
    }

    @Test
    public void getSubCommand() {
        assertThat(Command.getSubCommand(Token.newToken("add")), is(SubType.ADD));
        assertThat(Command.getSubCommand(Token.newToken("del")), is(SubType.DEL));
        assertThat(Command.getSubCommand(Token.newToken("connect")), is(SubType.CONNECT));
        assertThat(Command.getSubCommand(Token.newToken("list")), is(SubType.LIST));
        assertThat(Command.getSubCommand(Token.newToken("info")), is(SubType.INFO));
    }

}
