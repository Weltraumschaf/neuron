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
package de.weltraumschaf.neuron.event;

import de.weltraumschaf.commons.IO;
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.node.NodeFactory;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author ven Strittmatter <weltraumschaf@googlemail.com>
 */
public class EventHandlerTest {

    private final IO io = mock(IO.class);
    private final NodeFactory nodeFactory = new NodeFactory();
    private final EventHandler sut = new EventHandler(io);

    @Test
    public void update() {
        final Node source = nodeFactory.newNode();
        final Event event = new Event(Event.Type.MESSAGING, "foo", source);
        sut.update(event);
        verify(io, times(1)).println(String.format("Event: %s", event.toString()));
    }

    @Test @Ignore
    public void filterNode() {

    }

    @Test @Ignore
    public void filterType() {

    }

}
