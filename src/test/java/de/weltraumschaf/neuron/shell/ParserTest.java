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
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void parse_comandWithSubcommandAndOneArgument() throws SyntaxException {
        Command c = sut.parse("node add 1234");
        c = sut.parse("node add 1234");
        assertThat(c.getCommand(), is(MainType.NODE));
        assertThat(c.getSubCommand(), is(SubType.ADD));
        assertThat(c.getArguments().size(), is(1));
        Token<Integer> t = c.getArguments().get(0);
        assertThat(t.getType(), is(TokenType.INTEGER));
        assertThat(t.getValue(), is(1234));

        c = sut.parse("node del 5678");
        assertThat(c.getCommand(), is(MainType.NODE));
        assertThat(c.getSubCommand(), is(SubType.DEL));
        assertThat(c.getArguments().size(), is(1));
        t = c.getArguments().get(0);
        assertThat(t.getType(), is(TokenType.INTEGER));
        assertThat(t.getValue(), is(5678));

        c = sut.parse("node info 5678");
        assertThat(c.getCommand(), is(MainType.NODE));
        assertThat(c.getSubCommand(), is(SubType.INFO));
        assertThat(c.getArguments().size(), is(1));
        t = c.getArguments().get(0);
        assertThat(t.getType(), is(TokenType.INTEGER));
        assertThat(t.getValue(), is(5678));
    }

    @Test
    public void parse_throwExceptionOnMissingArgOnNodeAdd() throws SyntaxException {
        thrown.expect(SyntaxException.class);
        thrown.expectMessage("");
        sut.parse("node add");
    }

    @Test
    public void parse_throwExceptionOnMissingArgOnNodeDel() throws SyntaxException {
        thrown.expect(SyntaxException.class);
        thrown.expectMessage("");
        sut.parse("node del");
    }

    @Test
    public void parse_throwExceptionOnMissingArgOnNodeInfol() throws SyntaxException {
        thrown.expect(SyntaxException.class);
        thrown.expectMessage("");
        sut.parse("node info");
    }

    @Ignore
    @Test
    public void parse_throwExceptionOnSubcommandRequiresTwoArguments() {
        // http://weblogs.java.net/blog/johnsmart/archive/2009/09/27/testing-exceptions-junit-47
        thrown.expect(SyntaxException.class);
        thrown.expectMessage("");
    }

    @Test @Ignore
    public void parse_comandWithSubcommandAndTwoArgument() throws SyntaxException {
        Command c = sut.parse("node connect 1234 5678");
    }

}
