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
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.event.EventHandler;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeAddTest {

    private final IO io = mock(IO.class);
    private final Environment env = spy(new Environment(new EventHandler(io)));
    private final List<Token> args = Lists.newArrayList();

    @Test
    public void execute_amountIsLessThanOne() {
        args.add(Token.newToken(-3));
        final NodeAdd sut = new NodeAdd(env, io, args);
        sut.execute();
        verify(io, times(1)).println("Parameter AMOUNT must not be less than 1!");
        verify(env, never()).add();
    }

    @Test
    public void execute_withDefaultAmount() {
        final NodeAdd sut = new NodeAdd(env, io, args);
        sut.execute();
        verify(env, times(1)).add();
        verify(io, times(1)).println(String.format("Node with id 0 added%n"));
    }

    @Test
    public void execute_withAmountOfthree() {
        args.add(Token.newToken(3));
        final NodeAdd sut = new NodeAdd(env, io, args);
        sut.execute();
        verify(env, times(3)).add();
        verify(io, times(1)).println(String.format(
                "Node with id 0 added%nNode with id 1 added%nNode with id 2 added%n"));
    }

}
