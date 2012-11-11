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
package de.weltraumschaf.neuron;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeImplTest {

    @Test
    public void testHashCode() {
        final Node sut1 = new NodeImpl(23);
        final Node sut2 = new NodeImpl(23);
        final Node sut3 = new NodeImpl(42);

        assertThat(sut1.hashCode(), is(sut1.hashCode()));
        assertThat(sut1.hashCode(), is(sut2.hashCode()));
        assertThat(sut2.hashCode(), is(sut1.hashCode()));
        assertThat(sut2.hashCode(), is(sut2.hashCode()));

        assertThat(sut3.hashCode(), is(sut3.hashCode()));
        assertThat(sut3.hashCode(), is(not(sut1.hashCode())));
        assertThat(sut3.hashCode(), is(not(sut2.hashCode())));
    }

    @Test
    public void testEquals() {
        final Node sut1 = new NodeImpl(23);
        final Node sut2 = new NodeImpl(23);
        final Node sut3 = new NodeImpl(42);

        assertThat(sut1.equals(null), is(false));
        assertThat(sut1.equals("foo"), is(false));

        assertThat(sut1.equals(sut1), is(true));
        assertThat(sut1.equals(sut2), is(true));
        assertThat(sut2.equals(sut1), is(true));
        assertThat(sut2.equals(sut2), is(true));

        assertThat(sut1.equals(sut3), is(false));
        assertThat(sut3.equals(sut1), is(false));
        assertThat(sut2.equals(sut3), is(false));
        assertThat(sut3.equals(sut2), is(false));
    }

    @Test
    public void conectAndDisconect() {
        final Node sut = new NodeImpl(0);
        final Node n1 = new NodeImpl(1);
        final Node n2 = new NodeImpl(2);

        assertThat(sut.knows(sut), is(true));
        assertThat(sut.knows(0), is(true));
        assertThat(sut.knows(n1), is(false));
        assertThat(sut.knows(1), is(false));
        assertThat(sut.knows(n2), is(false));
        assertThat(sut.knows(2), is(false));

        sut.connect(n1);
        assertThat(sut.knows(sut), is(true));
        assertThat(sut.knows(0), is(true));
        assertThat(sut.knows(n1), is(true));
        assertThat(sut.knows(1), is(true));
        assertThat(sut.knows(n2), is(false));
        assertThat(sut.knows(2), is(false));

        sut.connect(n2);
        assertThat(sut.knows(sut), is(true));
        assertThat(sut.knows(0), is(true));
        assertThat(sut.knows(n1), is(true));
        assertThat(sut.knows(1), is(true));
        assertThat(sut.knows(n2), is(true));
        assertThat(sut.knows(2), is(true));

        sut.disconnect(n1);
        assertThat(sut.knows(sut), is(true));
        assertThat(sut.knows(0), is(true));
        assertThat(sut.knows(n1), is(false));
        assertThat(sut.knows(1), is(false));
        assertThat(sut.knows(n2), is(true));
        assertThat(sut.knows(2), is(true));

        sut.disconnect(n2);
        assertThat(sut.knows(sut), is(true));
        assertThat(sut.knows(0), is(true));
        assertThat(sut.knows(n1), is(false));
        assertThat(sut.knows(1), is(false));
        assertThat(sut.knows(n2), is(false));
        assertThat(sut.knows(2), is(false));
    }

    @Test(expected=IllegalArgumentException.class)
    public void conectSameNodeTwice() {
        final Node sut = new NodeImpl(0);
        final Node n1 = new NodeImpl(1);
        sut.connect(n1);
        sut.connect(n1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void disconnectUnConnectedNode() {
        final Node sut = new NodeImpl(0);
        final Node n1 = new NodeImpl(1);
        sut.disconnect(n1);
    }

}