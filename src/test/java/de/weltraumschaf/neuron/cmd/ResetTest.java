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
import de.weltraumschaf.neuron.shell.EventHandler;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ResetTest {

    private final IO io = mock(IO.class);
    private final Environment env = spy(new Environment(new EventHandler(io)));
    private final List<Token> args = Lists.newArrayList();
    private final Reset sut = new Reset(env, io, args);

    @Test
    public void execute() {
        sut.execute();
        verify(io, times(1)).println("Resetting...");
        verify(env, times(1)).reset();
    }

}