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
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ScannerTest {


    private final Scanner sut = new Scanner();

    @Test(expected = IllegalArgumentException.class)
    public void scan_nullArgument() throws SyntaxException {
        sut.scan(null);
    }

    @Test
    public void scan_emptyLine() throws SyntaxException {
        final List<Token> tokens = sut.scan("");
        assertThat(tokens.size(), is(0));
    }

    @Test
    public void scan_lineWithSingleString() throws SyntaxException {
        List<Token> tokens = sut.scan("help");
        assertThat(tokens.size(), is(1));
        Token<String> token = tokens.get(0);
        assertThat(token.getType(), is(TokenType.LITERAL));
        assertThat(token.getValue(), is("help"));

        tokens = sut.scan("exit");
        assertThat(tokens.size(), is(1));
        token = tokens.get(0);
        assertThat(token.getType(), is(TokenType.LITERAL));
        assertThat(token.getValue(), is("exit"));
    }

    @Test
    public void scan_lineWithMultipleStrings() throws SyntaxException {
        final List<Token> tokens = sut.scan("foo bar baz1");

        assertThat(tokens.size(), is(3));

        Token<String> token = tokens.get(0);
        assertThat(token.getType(), is(TokenType.LITERAL));
        assertThat(token.getValue(), is("foo"));

        token = tokens.get(1);
        assertThat(token.getType(), is(TokenType.LITERAL));
        assertThat(token.getValue(), is("bar"));

        token = tokens.get(2);
        assertThat(token.getType(), is(TokenType.LITERAL));
        assertThat(token.getValue(), is("baz1"));
    }

    @Test
    public void scan_lineWithSingleNumber() throws SyntaxException {
        final List<Token> tokens = sut.scan("1234");
        assertThat(tokens.size(), is(1));

        final Token<Integer> token = tokens.get(0);
        assertThat(token.getType(), is(TokenType.NUMBER));
        assertThat(token.getValue(), is(1234));
    }

    @Test
    public void scan_lineWithMultipleNumber() throws SyntaxException {
        final List<Token> tokens = sut.scan("1234 5678 90");
        assertThat(tokens.size(), is(3));

        Token<Integer> token = tokens.get(0);
        assertThat(token.getType(), is(TokenType.NUMBER));
        assertThat(token.getValue(), is(1234));

        token = tokens.get(1);
        assertThat(token.getType(), is(TokenType.NUMBER));
        assertThat(token.getValue(), is(5678));

        token = tokens.get(2);
        assertThat(token.getType(), is(TokenType.NUMBER));
        assertThat(token.getValue(), is(90));
    }

    @Test
    public void scan_lineWithStringsAndNumbers() throws SyntaxException {
        Token<Integer> intToken;
        Token<String> strToken;
        List<Token> tokens = sut.scan("foo 1234 5678 bar");

        assertThat(tokens.size(), is(4));
        strToken = tokens.get(0);
        assertThat(strToken.getType(), is(TokenType.LITERAL));
        assertThat(strToken.getValue(), is("foo"));
        intToken = tokens.get(1);
        assertThat(intToken.getType(), is(TokenType.NUMBER));
        assertThat(intToken.getValue(), is(1234));
        intToken = tokens.get(2);
        assertThat(intToken.getType(), is(TokenType.NUMBER));
        assertThat(intToken.getValue(), is(5678));
        strToken = tokens.get(3);
        assertThat(strToken.getType(), is(TokenType.LITERAL));
        assertThat(strToken.getValue(), is("bar"));

        tokens = sut.scan("1234 foo bar 5678");
        assertThat(tokens.size(), is(4));
        intToken = tokens.get(0);
        assertThat(intToken.getType(), is(TokenType.NUMBER));
        assertThat(intToken.getValue(), is(1234));
        strToken = tokens.get(1);
        assertThat(strToken.getType(), is(TokenType.LITERAL));
        assertThat(strToken.getValue(), is("foo"));
        strToken = tokens.get(2);
        assertThat(strToken.getType(), is(TokenType.LITERAL));
        assertThat(strToken.getValue(), is("bar"));
        intToken = tokens.get(3);
        assertThat(intToken.getType(), is(TokenType.NUMBER));
        assertThat(intToken.getValue(), is(5678));

    }

    @Test(expected = SyntaxException.class)
    public void scan_lineWithMaliciousNumber() throws SyntaxException {
        sut.scan("1234foo");
    }

}
