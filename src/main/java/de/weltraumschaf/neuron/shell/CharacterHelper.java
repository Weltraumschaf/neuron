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
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class CharacterHelper {

    /**
     * Private constructor for pure static utility class.
     */
    private CharacterHelper() {
        super();
    }

    /**
     * Checks whether a character is inside a given character range (included).
     *
     * Throws IllegalArgumentException if end is less than start.
     *
     * @param character character to check
     * @param start including range
     * @param end including range
     * @return true if character is in range, unless false
     */
    static boolean isCharInRange(final char character, final char start, final char end) {
        if (end < start) {
            throw new IllegalArgumentException("End must be greater or equal than start!");
        }

        return start <= character && character <= end;
    }

    /**
     * Checks whether a character is a letter [a-zA-Z].
     *
     * @param character a single character
     * @return true if character is a letter, unless false
     */
    static boolean isAlpha(final char character) {
        return isCharInRange(character, 'a', 'z') || isCharInRange(character, 'A', 'Z');
    }

    /**
     * Checks whether a character is a number [0-9].
     *
     * @param character a single character
     *
     * @return true if character is a number, unless false
     */
    static boolean isNum(final char character) {
        return isCharInRange(character, '0', '9');
    }

    /**
     * Checks whether a character is a number or alpha [0-9a-zA-Z].
     *
     * @param character a single character
     *
     * @return true if character is a letter or number, unless false
     */
    static boolean isAlphaNum(final char character) {
        return isAlpha(character) || isNum(character);
    }

    /**
     * Checks whether a character is a whitespace.
     *
     * White spaces are \t, \n, \r, and ' '.
     *
     * @param character a single character
     * @return true if character is a whitespace character, unless false
     */
    static boolean isWhiteSpace(final char character) {
        return ' ' == character || '\t' == character || '\n' == character || '\r' == character;
    }

    /**
     * Checks whether a character is a quote character.
     *
     * Quotes are ' and ".
     *
     * @param character a single character
     * @return true if character is a whitespace character, unless false
     */
    static boolean isQuote(final char character) {
        return '\'' == character || '"' == character;
    }

}
