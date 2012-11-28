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

import de.weltraumschaf.commons.shell.LiteralCommandMap;
import de.weltraumschaf.commons.shell.MainCommandType;
import de.weltraumschaf.commons.shell.SubCommandType;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NeuronLiteralCommandMapTest {

    private static enum BadMainType implements MainCommandType {
        FOO("foo"), BAR("bar"), BAZ("baz");

        /**
         * Literal command string used in shell.
         */
        private final String literal;

        /**
         * Initialize name.
         *
         * @param name literal shell command string
         */
        private BadMainType(final String name) {
            this.literal = name;
        }

        @Override
        public String toString() {
            return literal;
        }
    }

    private static enum BadSubType implements SubCommandType {
        FOO("foo"), BAR("bar"), BAZ("baz");

        /**
         * Literal command string used in shell.
         */
        private final String literal;

        /**
         * Initialize name.
         *
         * @param name literal shell command string
         */
        private BadSubType(final String name) {
            this.literal = name;
        }

        @Override
        public String toString() {
            return literal;
        }
    }

    private final LiteralCommandMap sut = new NeuronLiteralCommandMap();

    @Test
    public void isCommand() {
        assertThat(sut.isCommand(NeuronMainType.DUMP.toString()), is(true));
        assertThat(sut.isCommand(NeuronMainType.EXIT.toString()), is(true));
        assertThat(sut.isCommand(NeuronMainType.HELP.toString()), is(true));
        assertThat(sut.isCommand(NeuronMainType.MESSAGE.toString()), is(true));
        assertThat(sut.isCommand(NeuronMainType.NODE.toString()), is(true));
        assertThat(sut.isCommand(NeuronMainType.RESET.toString()), is(true));
        assertThat(sut.isCommand(NeuronMainType.SAMPLE.toString()), is(true));
        assertThat(sut.isCommand(BadMainType.FOO.toString()), is(false));
        assertThat(sut.isCommand(BadMainType.BAR.toString()), is(false));
        assertThat(sut.isCommand(BadMainType.BAZ.toString()), is(false));
    }

    @Test
    public void isSubCommand() {
        assertThat(sut.isSubCommand(NeuronSubType.ADD.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.BITREE.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.CONNECT.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.DEL.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.DISCONNECT.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.DOT.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.INFO.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.LIST.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.LISTEN.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.TREE.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.UNLISTEN.toString()), is(true));
        assertThat(sut.isSubCommand(NeuronSubType.NONE.toString()), is(false));
        assertThat(sut.isSubCommand(BadSubType.FOO.toString()), is(false));
        assertThat(sut.isSubCommand(BadSubType.BAR.toString()), is(false));
        assertThat(sut.isSubCommand(BadSubType.BAZ.toString()), is(false));
    }

}
