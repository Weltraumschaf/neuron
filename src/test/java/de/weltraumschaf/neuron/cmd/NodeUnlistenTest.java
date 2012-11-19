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
import de.weltraumschaf.neuron.event.EventHandler;
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.node.NodeFactory;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeUnlistenTest {

    private final IO io = mock(IO.class);
    private final Environment env = new Environment(new EventHandler(io));
    private final List<Token> args = Lists.newArrayList();
    private final NodeFactory nodeFactory = new NodeFactory();
    // CHECKSTYLE:OFF
    @Rule public ExpectedException thrown = ExpectedException.none();
    // CHECKSTYLE:ON

    @Test
    public void execute_nodeDoesNotExist() {
        args.add(Token.newNumberToken(23));
        final Command sut = new NodeUnlisten(env, io, args);
        sut.execute();
        verify(io, times(1)).println("There is no node with id '23'!");
    }

    @Test
    public void execute_nodeDoesExist() {
        final Node node = spy(nodeFactory.newNode());
        env.add(node);
        args.add(Token.newNumberToken(node.getId()));
        final Command sut = new NodeUnlisten(env, io, args);
        sut.execute();
        verify(io, times(1)).println(String.format("Stop listening for events emmitted by node '%d'.", node.getId()));
        verify(node, times(1)).deleteObserver(env.getHandler());
    }

    @Test
    public void execute_unlistenAllNodes() {
        final Node node1 = spy(nodeFactory.newNode());
        env.add(node1);
        final Node node2 = spy(nodeFactory.newNode());
        env.add(node2);
        final Node node3 = spy(nodeFactory.newNode());
        env.add(node3);

        args.add(Token.newLiteralToken("all"));
        final Command sut = new NodeUnlisten(env, io, args);
        sut.execute();

        verify(io, times(1)).println("Stop listening for events emmitted by all nodes.");
        verify(node1, times(1)).deleteObserver(env.getHandler());
        verify(node2, times(1)).deleteObserver(env.getHandler());
        verify(node3, times(1)).deleteObserver(env.getHandler());
    }

    @Test
    public void execute_throwExceptionIfUnrecognizableLiteralArgument() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unrecognizable argument 'foobar'!");
        args.add(Token.newLiteralToken("foobar"));
        final Command sut = new NodeUnlisten(env, io, args);
        sut.execute();
    }

    @Test
    public void execute_throwExceptionIfUnrecognizableArgument() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Unrecognizable argument 'foobar'!");
        args.add(Token.newStringToken("foobar"));
        final Command sut = new NodeUnlisten(env, io, args);
        sut.execute();
    }

}
