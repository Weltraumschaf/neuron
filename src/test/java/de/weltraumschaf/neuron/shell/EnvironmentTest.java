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

import de.weltraumschaf.neuron.Node;
import de.weltraumschaf.neuron.NodeFactory;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class EnvironmentTest {

    private final NodeFactory factory = new NodeFactory();
    private final Environment sut = new Environment();

    @Test
    public void addNodes() {
        assertThat(sut.size(), is(0));
        sut.add(factory.newNode(0));
        assertThat(sut.size(), is(1));
        sut.add(factory.newNode(1));
        assertThat(sut.size(), is(2));
        sut.add(factory.newNode(2));
        assertThat(sut.size(), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionOnDuplicateIds() {
        sut.add(factory.newNode(1));
        sut.add(factory.newNode(1));
    }

    @Test
    public void addNodesWithAutomaticId() {
        assertThat(sut.size(), is(0));
        final Node n1 = sut.add();
        assertThat(n1.getId(), is(0));
        assertThat(sut.size(), is(1));
        final Node n2 = sut.add();
        assertThat(n2.getId(), is(1));
        assertThat(sut.size(), is(2));
    }

    @Test
    public void reset() {
        assertThat(sut.size(), is(0));
        assertThat(sut.getNodes().isEmpty(), is(true));
        sut.add();
        sut.add();
        sut.add();
        assertThat(sut.size(), is(3));
        assertThat(sut.getNodes().get(2).getId(), is(2));
        sut.reset();
        assertThat(sut.size(), is(0));
        assertThat(sut.getNodes().isEmpty(), is(true));
        sut.add();
        assertThat(sut.size(), is(1));
        assertThat(sut.getNodes().get(0).getId(), is(0));
    }

    @Test
    public void removeNode() {
        final Node n1 = spy(factory.newNode(1));
        sut.add(n1);
        final Node n2 = spy(factory.newNode(2));
        n2.connect(n1);
        sut.add(n2);
        final Node n3 = spy(factory.newNode(3));
        sut.add(n3);

        assertThat(sut.size(), is(3));
        sut.remove(n1);
        assertThat(sut.size(), is(2));
        sut.remove(n1);
        assertThat(sut.size(), is(2));

        verify(n2, times(1)).hasNeighbor(n1);
        verify(n2, times(1)).disconnect(n1);
        verify(n3, times(1)).hasNeighbor(n1);
        verify(n3, never()).disconnect(n1);
    }
}
