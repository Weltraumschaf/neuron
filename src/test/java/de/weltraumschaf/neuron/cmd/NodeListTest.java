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
package de.weltraumschaf.neuron.cmd;

import com.google.common.collect.Lists;
import de.weltraumschaf.commons.IO;
import de.weltraumschaf.commons.shell.Token;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.event.EventHandler;
import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
public class NodeListTest {

    private final IO io = mock(IO.class);
    private final Environment env = new Environment(new EventHandler(io));
    private final List<Token> args = Lists.newArrayList();

    @Test
    public void execute_noNodes() {
        final NodeList sut = new NodeList(env, io, args);
        sut.execute();
        verify(io, times(1)).println(String.format("No nodes created.%n"));
    }

    @Test
    public void execute_oneNodes() {
        env.add();
        final NodeList sut = new NodeList(env, io, args);
        sut.execute();
        verify(io, times(1)).println(String.format(
                "1 node created.%n%n"
                + "Existing nodes:%n"
                + "  Node ID: 0 Neigbors: 0%n"));
    }

    @Test
    public void execute_threeNodes() {
        env.add();
        env.add();
        env.add();
        final NodeList sut = new NodeList(env, io, args);
        sut.execute();
        verify(io, times(1)).println(String.format(
                "3 nodes created.%n%n"
                + "Existing nodes:%n"
                + "  Node ID: 0 Neigbors: 0%n"
                + "  Node ID: 1 Neigbors: 0%n"
                + "  Node ID: 2 Neigbors: 0%n"));
    }

}
