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
import de.weltraumschaf.neuron.node.NodeFactory;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.event.EventHandler;
import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeDisconnectTest {

    private final IO io = mock(IO.class);
    private final Environment env = spy(new Environment(new EventHandler(io)));
    private final List<Token> args = Lists.newArrayList();
    private final NodeFactory factory = new NodeFactory();

    @Test(expected = IndexOutOfBoundsException.class)
    public void execute_noArguments() {
        final NodeDisconnect sut = new NodeDisconnect(env, io, args);
        sut.execute();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void execute_oneArguments() {
        args.add(Token.newNumberToken(23));
        final NodeDisconnect sut = new NodeDisconnect(env, io, args);
        sut.execute();
    }

    @Test
    public void execute_sourceIdDoesNotExist() {
        args.add(Token.newNumberToken(23));
        args.add(Token.newNumberToken(42));
        final NodeDisconnect sut = new NodeDisconnect(env, io, args);
        sut.execute();
        verify(io, times(1)).println("Node with id 23 does not exist!");
    }

    @Test
    public void execute_destiationIdDoesNotExist() {
        final Node source = env.add();
        args.add(Token.newNumberToken(source.getId()));
        args.add(Token.newNumberToken(42));
        final NodeDisconnect sut = new NodeDisconnect(env, io, args);
        sut.execute();
        verify(io, times(1)).println("Node with id 42 does not exist!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void execute_unconnectedNodes() {
        final Node source = spy(factory.newNode());
        env.add(source);
        args.add(Token.newNumberToken(source.getId()));

        final Node destination = spy(factory.newNode());
        env.add(destination);
        args.add(Token.newNumberToken(destination.getId()));

        final NodeDisconnect sut = new NodeDisconnect(env, io, args);
        sut.execute();
    }

    @Test
    public void execute() {
        final Node source = spy(factory.newNode());
        env.add(source);
        args.add(Token.newNumberToken(source.getId()));

        final Node destination = spy(factory.newNode());
        env.add(destination);
        args.add(Token.newNumberToken(destination.getId()));
        source.connect(destination);

        final NodeDisconnect sut = new NodeDisconnect(env, io, args);
        sut.execute();
        verify(source, times(1)).disconnect(destination);
        verify(io, times(1)).println(String.format("Disconect nodes: %d -! %d.",
                                                   source.getId(),
                                                   destination.getId()));
    }

}
