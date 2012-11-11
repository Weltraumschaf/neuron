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
import java.util.List;

/**
 * Scans the input line from an interactive shell.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class Scanner {

    List<Token> scan(final String line) throws SyntaxException {
        if (null == line) {
            throw new IllegalArgumentException("Line must not be null!");
        }

        final List<Token> tokens = Lists.newArrayList();

        if ( ! line.isEmpty()) {
            scan(tokens, new CharacterStream(line));
        }

        return tokens;
    }

    private void scan(final List<Token> tokens, final CharacterStream characterStream) throws SyntaxException {
        while (characterStream.hasNext()) {
            final char currentChar = characterStream.next();

            if (CharacterHelper.isAlpha(currentChar)) {
                tokens.add(scanAlphaNum(characterStream));
            } else if (CharacterHelper.isNum(currentChar)) {
                tokens.add(scanNumber(characterStream));
            }
        }
    }

    private Token scanAlphaNum(final CharacterStream characterStream) {
        final StringBuilder value = new StringBuilder();
        value.append(characterStream.current());

        while (characterStream.hasNext()) {
            final char currentChar = characterStream.next();

            if (CharacterHelper.isWhiteSpace(currentChar)) {
                break;
            }

            value.append(currentChar);
        }

        return Token.newToken(value.toString());
    }

    private Token scanNumber(final CharacterStream characterStream) throws SyntaxException {
        final StringBuilder value = new StringBuilder();

        value.append(characterStream.current());

        while (characterStream.hasNext()) {
            final char currentChar = characterStream.next();

            if (CharacterHelper.isWhiteSpace(currentChar)) {
                break;
            }

            if ( ! CharacterHelper.isNum(currentChar)) {
                throw new SyntaxException(String.format("Bad character '%s' in number starting with '%s'!", currentChar, value.toString()));
            }

            value.append(currentChar);
        }

        return Token.newToken(Integer.valueOf(value.toString()));
    }

}
