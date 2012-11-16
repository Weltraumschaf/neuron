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
package de.weltraumschaf.neuron.node;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
public class EventTest {

    @Test
    public void testToString() {
        final Event sut = new Event(Event.Type.MESSAGING, "foo");
        assertThat(sut.toString(), is("[MESSAGING] foo"));
    }

}
