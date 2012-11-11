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
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ParserTest {

    private final Parser sut = new Parser(new Scanner());

    @Test
    public void parse_comand() throws SyntaxException {
        Command c = sut.parse("help");
        assertThat(c.getCommand(), is(MainType.HELP));
        assertThat(c.getSubCommand(), is(SubType.NONE));
        assertThat(c.getArguments().size(), is(0));

        c = sut.parse("reset");
        assertThat(c.getCommand(), is(MainType.RESET));
        assertThat(c.getSubCommand(), is(SubType.NONE));
        assertThat(c.getArguments().size(), is(0));

        c = sut.parse("exit");
        assertThat(c.getCommand(), is(MainType.EXIT));
        assertThat(c.getSubCommand(), is(SubType.NONE));
        assertThat(c.getArguments().size(), is(0));
    }

    @Test @Ignore("Not used yet!")
    public void parse_comandWithOneArgument() {
    }

    @Test @Ignore("Not used yet!")
    public void parse_comandWithTwoArgument() {
    }

    @Test
    public void parse_comandWithSubcommand() throws SyntaxException {
        Command c = sut.parse("node add");
        assertThat(c.getCommand(), is(MainType.NODE));
        assertThat(c.getSubCommand(), is(SubType.ADD));
        assertThat(c.getArguments().size(), is(0));

        c = sut.parse("node list");
        assertThat(c.getCommand(), is(MainType.NODE));
        assertThat(c.getSubCommand(), is(SubType.LIST));
        assertThat(c.getArguments().size(), is(0));
    }

    @Test @Ignore
    public void parse_comandWithSubcommandAndOneArgument() throws SyntaxException {
        Command c = sut.parse("node add 1234");
        c = sut.parse("node del 1234");

        c = sut.parse("node info 5678");
    }

    @Test @Ignore
    public void parse_comandWithSubcommandAndTwoArgument() throws SyntaxException {
        Command c = sut.parse("node connect 1234 5678");
    }

}
