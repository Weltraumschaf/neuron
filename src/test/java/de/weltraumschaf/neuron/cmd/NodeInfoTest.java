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
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.event.EventHandler;
import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
public class NodeInfoTest {

    private final IO io = mock(IO.class);
    private final Environment env = new Environment(new EventHandler(io));

    @Test
    public void execute_nodeDoesNotExists() {
        final List<Token> args = Lists.newArrayList();
        args.add(Token.newNumberToken(23));
        final Command sut = new NodeInfo(env, io, args);
        sut.execute();
        verify(io, times(1)).println("Node with id 23 does not exist!");
    }

    @Test
    public void execute_nodeHasNoNeighbots() {
        final Node inspectedNode = env.add();
        final List<Token> args = Lists.newArrayList();
        args.add(Token.newNumberToken(inspectedNode.getId()));
        final Command sut = new NodeInfo(env, io, args);
        sut.execute();
        verify(io, times(1)).println(String.format("Node ID: %d Neigbors: 0%n"
                + "Neighbors:%n"
                + "  Has no neighbors!%n%n"
                + "Received messages:%n"
                + "  Has no messages received!%n", inspectedNode.getId()));
    }

    @Test
    public void execute_nodeHasOneNeighbors() {
        final Node inspectedNode = env.add();
        final Node neighborNode = env.add();
        inspectedNode.connect(neighborNode);
        final List<Token> args = Lists.newArrayList();
        args.add(Token.newNumberToken(inspectedNode.getId()));
        final Command sut = new NodeInfo(env, io, args);
        sut.execute();
        verify(io, times(1)).println(String.format("Node ID: %d Neigbors: 1%n"
                + "Neighbors:%n"
                + "  %s%n%n"
                + "Received messages:%n"
                + "  Has no messages received!%n", inspectedNode.getId(), neighborNode));
    }

    @Test
    public void execute_nodeHasThreeNeighbors() {
        final Node inspectedNode = env.add();
        final Node neighborNode1 = env.add();
        final Node neighborNode2 = env.add();
        final Node neighborNode3 = env.add();
        inspectedNode.connect(neighborNode1);
        inspectedNode.connect(neighborNode2);
        inspectedNode.connect(neighborNode3);
        final List<Token> args = Lists.newArrayList();
        args.add(Token.newNumberToken(inspectedNode.getId()));
        final Command sut = new NodeInfo(env, io, args);
        sut.execute();
        verify(io, times(1)).println(String.format("Node ID: %d Neigbors: 3%n"
                + "Neighbors:%n"
                + "  %s%n  %s%n  %s%n%n"
                + "Received messages:%n"
                + "  Has no messages received!%n",
                inspectedNode.getId(), neighborNode1, neighborNode2, neighborNode3));
    }

}
