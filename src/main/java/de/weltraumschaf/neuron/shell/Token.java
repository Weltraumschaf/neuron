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

/**
 * Represent a token scanned from interactive shell input.
 *
 * @param <T> Type of the token value.
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Token<T> {

    /**
     * Type of token.
     */
    private final TokenType type;

    /**
     * Value of the token.
     */
    private final T value;

    /**
     * Hide constructor to force usage of factory methods.
     *
     * @param type Token type.
     * @param value Token value.
     */
    private Token(final TokenType type, final T value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Creates a string token.
     *
     * @param value token value
     * @return new instance
     */
    static Token<String> newToken(final String value) {
        return new Token<String>(TokenType.LITERAL, value);
    }

    /**
     * Creates a integer token.
     *
     * @param value token value
     * @return new instance
     */
    static Token<Integer> newToken(final Integer value) {
        return new Token<Integer>(TokenType.INTEGER, value);
    }

    /**
     * Get the tokens type.
     *
     * @return type enum
     */
    TokenType getType() {
        return type;
    }

    /**
     * Get the tokens value.
     *
     * @return token value.
     */
    T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add("type", type)
                      .add("value", value)
                      .toString();
    }

}
