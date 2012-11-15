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
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.EventHandler;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeDelTest {

    private final IO io = mock(IO.class);
    private final Environment env = spy(new Environment(new EventHandler(io)));
    private final List<Token> args = Lists.newArrayList();

    @Test
    public void execute_deleteNotExistingNode() {
        args.add(Token.newToken(3));
        final NodeDel sut = new NodeDel(env, io, args);
        sut.execute();
        verify(io, times(1)).println("Node with id 3 does not exist!");
    }

    @Test
    public void execute() {
        final Node n = env.add();
        args.add(Token.newToken(n.getId()));
        final NodeDel sut = new NodeDel(env, io, args);
        sut.execute();
        verify(io, never()).println(anyString());
        verify(env, times(1)).remove(n.getId());
    }

}
