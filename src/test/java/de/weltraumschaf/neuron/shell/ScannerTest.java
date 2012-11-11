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

import java.util.List;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ScannerTest {


    private final Scanner sut = new Scanner();

    @Test(expected=IllegalArgumentException.class)
    public void scan_nullArgument() {
        sut.scan(null);
    }

    @Test
    public void scan_emptyLine() {
        List<Token> tokens = sut.scan("");
        assertThat(tokens.size(), is(0));
    }

    @Test
    public void scan_lineWithSingleString() {
        List<Token> tokens = sut.scan("help");
        assertThat(tokens.size(), is(1));
        Token<String> token = tokens.get(0);
        assertThat(token.getType(), is(TokenType.STRING));
        assertThat(token.getValue(), is("help"));

        tokens = sut.scan("exit");
        assertThat(tokens.size(), is(1));
        token = tokens.get(0);
        assertThat(token.getType(), is(TokenType.STRING));
        assertThat(token.getValue(), is("exit"));
    }

    @Test
    public void scan_lineWithMultipleStrings() {
        final List<Token> tokens = sut.scan("foo bar baz1");

        assertThat(tokens.size(), is(3));

        Token<String> token = tokens.get(0);
        assertThat(token.getType(), is(TokenType.STRING));
        assertThat(token.getValue(), is("foo"));

        token = tokens.get(1);
        assertThat(token.getType(), is(TokenType.STRING));
        assertThat(token.getValue(), is("bar"));

        token = tokens.get(2);
        assertThat(token.getType(), is(TokenType.STRING));
        assertThat(token.getValue(), is("baz1"));
    }

}
