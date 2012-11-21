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

import de.weltraumschaf.neuron.event.Observer;
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.shell.Environment;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class DotGeneratorTest {

    private final Environment env = new Environment(mock(Observer.class));
    private final DotGenerator sut = new DotGenerator(env);

    @Before
    public void reset() {
        env.reset();
    }

    @Test
    public void graphWithNoNodes() {
        assertThat(sut.toString(), is(String.format(
                  "graph neuron {%n"
                + "}")));
    }

    public void graphWithOneNodes() {
        final Node n1 = env.add();
        assertThat(sut.toString(), is(String.format(
                  "graph neuron {%n"
                + "  %d%n"
                + "}", n1.getId())));
    }

    @Test
    public void graphWithTwoUnconnectedNodes() {
        final Node n1 = env.add();
        final Node n2 = env.add();
        assertThat(sut.toString(), is(String.format(
                  "graph neuron {%n"
                + "  %d%n"
                + "  %d%n"
                + "}", n1.getId(), n2.getId())));
    }

    @Test
    public void graphWithTwoConnectedNodes() {
        final Node n1 = env.add();
        final Node n2 = env.add();
        n1.connect(n2);
        assertThat(sut.toString(), is(String.format(
                  "graph neuron {%n"
                + "  %d -- %d%n"
                + "  %d%n"
                + "}", n1.getId(), n2.getId(), n2.getId())));
    }

    @Test
    public void graphWithThreeNodesInOneLine() {
        final Node n1 = env.add();
        final Node n2 = env.add();
        final Node n3 = env.add();
        n1.connect(n2);
        n2.connect(n3);
        assertThat(sut.toString(), is(String.format(
                  "graph neuron {%n"
                + "  %d -- %d%n"
                + "  %d -- %d%n"
                + "  %d%n"
                + "}", n1.getId(), n2.getId(), n2.getId(), n3.getId(), n3.getId())));
    }

}
