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
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.node.NodeFactory;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.EventHandler;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
public class NodeConnectTest {

    private final IO io = mock(IO.class);
    private final Environment env = spy(new Environment(new EventHandler(io)));
    private final List<Token> args = Lists.newArrayList();
    private final NodeFactory factory = new NodeFactory();

    @Test(expected = IndexOutOfBoundsException.class)
    public void execute_noArguments() {
        final NodeConnect sut = new NodeConnect(env, io, args);
        sut.execute();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void execute_oneArguments() {
        args.add(Token.newToken(23));
        final NodeConnect sut = new NodeConnect(env, io, args);
        sut.execute();
    }

    @Test
    public void execute_sourceIdDoesNotExist() {
        args.add(Token.newToken(23));
        args.add(Token.newToken(42));
        final NodeConnect sut = new NodeConnect(env, io, args);
        sut.execute();
        verify(io, times(1)).println("Node with id 23 does not exist!");
    }

    @Test
    public void execute_destiationIdDoesNotExist() {
        final Node source = env.add();
        args.add(Token.newToken(source.getId()));
        args.add(Token.newToken(42));
        final NodeConnect sut = new NodeConnect(env, io, args);
        sut.execute();
        verify(io, times(1)).println("Node with id 42 does not exist!");
    }

    @Test
    public void execute() {
        final Node source = spy(factory.newNode());
        env.add(source);
        args.add(Token.newToken(source.getId()));

        final Node destination = spy(factory.newNode());
        env.add(destination);
        args.add(Token.newToken(destination.getId()));

        final NodeConnect sut = new NodeConnect(env, io, args);
        sut.execute();
        verify(source, times(1)).connect(destination);
        verify(io, times(1)).println(String.format("Conected nodes: %d -> %d.",
                                                   source.getId(),
                                                   destination.getId()));
    }

}
