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

/**
 * Access a string as stream of characters.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class CharacterStream {

    /**
     * Accessed string.
     */
    private final String input;

    /**
     * Current character position.
     */
    private int index = -1;

    /**
     * Initializes stream with string.
     *
     * @param input Streamed string.
     */
    public CharacterStream(final String input) {
        this.input = input;
    }

    /**
     * Returns next character.
     *
     * @return next character
     * @throws IndexOutOfBoundsException if, there are no more characters.
     */
    char next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more next characters!");
        }

        ++index;
        return current();
    }

    /**
     * True if there are more characters.
     *
     * @return True if there are no more characters.
     */
    boolean hasNext() {
        return index < input.length() - 1;
    }

    /**
     * Returns the current character.
     *
     * If {@link #next()} not yet called, it is called implicitly.
     *
     * @return The current character.
     */
    char current() {
        if (-1 == index) {
            next();
        }

        return input.charAt(index);
    }

}
