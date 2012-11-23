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
package de.weltraumschaf.neuron.node;

import de.weltraumschaf.commons.IO;
import de.weltraumschaf.neuron.event.EventHandler;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeImplTest {

    @Test
    public void testHashCode() {
        final Node sut1 = new NodeImpl(23);
        final Node sut2 = new NodeImpl(23);
        final Node sut3 = new NodeImpl(42);

        assertThat(sut1.hashCode(), is(sut1.hashCode()));
        assertThat(sut1.hashCode(), is(sut2.hashCode()));
        assertThat(sut2.hashCode(), is(sut1.hashCode()));
        assertThat(sut2.hashCode(), is(sut2.hashCode()));

        assertThat(sut3.hashCode(), is(sut3.hashCode()));
        assertThat(sut3.hashCode(), is(not(sut1.hashCode())));
        assertThat(sut3.hashCode(), is(not(sut2.hashCode())));
    }

    @Test
    public void testEquals() {
        final Node sut1 = new NodeImpl(23);
        final Node sut2 = new NodeImpl(23);
        final Node sut3 = new NodeImpl(42);

        // CHECKSTYLE:OFF
        assertThat(sut1.equals(null), is(false)); //NOPMD
        assertThat(sut1.equals("foo"), is(false));//NOPMD
        // CHECKSTYLE:ON

        assertThat(sut1.equals(sut1), is(true));
        assertThat(sut1.equals(sut2), is(true));
        assertThat(sut2.equals(sut1), is(true));
        assertThat(sut2.equals(sut2), is(true));

        assertThat(sut1.equals(sut3), is(false));
        assertThat(sut3.equals(sut1), is(false));
        assertThat(sut2.equals(sut3), is(false));
        assertThat(sut3.equals(sut2), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void connectNull() {
        final Node sut = new NodeImpl(0);
        sut.connect(null);
    }

    @Test(expected = NullPointerException.class)
    public void disconnectNull() {
        final Node sut = new NodeImpl(0);
        sut.disconnect(null);
    }

    @Test
    public void conectAndDisconect() {
        final Node sut = new NodeImpl(0);
        final Node n1 = new NodeImpl(1);
        final Node n2 = new NodeImpl(2);

        assertThat(sut.hasNeighbor(sut), is(false));
        assertThat(sut.hasNeighbor(0), is(false));
        assertThat(sut.hasNeighbor(n1), is(false));
        assertThat(sut.hasNeighbor(1), is(false));
        assertThat(sut.hasNeighbor(n2), is(false));
        assertThat(sut.hasNeighbor(2), is(false));

        sut.connect(n1);
        assertThat(sut.hasNeighbor(sut), is(false));
        assertThat(sut.hasNeighbor(0), is(false));
        assertThat(sut.hasNeighbor(n1), is(true));
        assertThat(sut.hasNeighbor(1), is(true));
        assertThat(sut.hasNeighbor(n2), is(false));
        assertThat(sut.hasNeighbor(2), is(false));

        sut.connect(n2);
        assertThat(sut.hasNeighbor(sut), is(false));
        assertThat(sut.hasNeighbor(0), is(false));
        assertThat(sut.hasNeighbor(n1), is(true));
        assertThat(sut.hasNeighbor(1), is(true));
        assertThat(sut.hasNeighbor(n2), is(true));
        assertThat(sut.hasNeighbor(2), is(true));

        sut.disconnect(n1);
        assertThat(sut.hasNeighbor(sut), is(false));
        assertThat(sut.hasNeighbor(0), is(false));
        assertThat(sut.hasNeighbor(n1), is(false));
        assertThat(sut.hasNeighbor(1), is(false));
        assertThat(sut.hasNeighbor(n2), is(true));
        assertThat(sut.hasNeighbor(2), is(true));

        sut.disconnect(n2);
        assertThat(sut.hasNeighbor(sut), is(false));
        assertThat(sut.hasNeighbor(0), is(false));
        assertThat(sut.hasNeighbor(n1), is(false));
        assertThat(sut.hasNeighbor(1), is(false));
        assertThat(sut.hasNeighbor(n2), is(false));
        assertThat(sut.hasNeighbor(2), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void conectSameNodeTwice() {
        final Node sut = new NodeImpl(0);
        final Node n1 = new NodeImpl(1);
        sut.connect(n1);
        sut.connect(n1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void disconnectUnConnectedNode() {
        final Node sut = new NodeImpl(0);
        final Node n1 = new NodeImpl(1);
        sut.disconnect(n1);
    }

    @Test
    public void toString_NoNeighbors() {
        final Node sut = new NodeImpl(23);
        assertThat(sut.toString(), is("Node ID: 23 Neigbors: 0"));
    }

    @Test
    public void toString_twoNeighbors() {
        final Node sut = new NodeImpl(23);
        sut.connect(new NodeImpl(1));
        sut.connect(new NodeImpl(2));
        assertThat(sut.toString(), is("Node ID: 23 Neigbors: 2"));
    }

    @Test
    public void hasNeighbors() {
        final Node sut = new NodeImpl(23);
        assertThat(sut.hasNeighbors(), is(false));
        sut.connect(new NodeImpl(1));
        assertThat(sut.hasNeighbors(), is(true));
    }

    @Test
    public void hasNeighbor() {
        final Node sut = new NodeImpl(23);
        final Node n = new NodeImpl(1);
        assertThat(sut.hasNeighbor(n), is(false));
        sut.connect(n);
        assertThat(sut.hasNeighbor(n), is(true));
    }

    @Test
    public void getNeighbors() {
        final Node sut = new NodeImpl(23);
        final NodeImpl n1 = new NodeImpl(1);
        final NodeImpl n2 = new NodeImpl(2);
        sut.connect(n1);
        sut.connect(n2);
        final List<Node> neighbors = sut.getNeighbors();
        assertThat(neighbors.size(), is(2));
        assertThat(neighbors.contains(n1), is(true));
        assertThat(neighbors.contains(n2), is(true));
    }

    @Test
    public void getNeighbor() {
        final NodeImpl sut = new NodeImpl(23);
        final Node n = new NodeImpl(1);
        sut.connect(n);
        assertThat(sut.getNeighbor(1), is(n));
    }

    @Test
    public void sendMessageToSelf() {
        final Node sut = new NodeImpl(23);
        final Message message = new Message("foo", 23, 23);
        sut.send(message);
        assertThat(message.isDelivered(), is(true));
        final List<Message> messages = sut.getInbox().getMessages();
        assertThat(messages.size(), is(1));
        assertThat(messages.get(0), is(message));
    }

    @Test
    public void sendMessageToNeighbor() {
        final Node sut = new NodeImpl(23);
        final Node neighbor = new NodeImpl(42);
        sut.connect(neighbor);
        final Message message = new Message("foo", 23, 42);
        sut.send(message);
        assertThat(message.isDelivered(), is(true));
        assertThat(sut.getInbox().getMessages().size(), is(0));
        final List<Message> messages = neighbor.getInbox().getMessages();
        assertThat(messages.size(), is(1));
        assertThat(messages.get(0), is(message));
    }

    @Test
    public void sendMessageOverSomeNodes() {
        final Node n1 = new NodeImpl(1);
        final Node n2 = new NodeImpl(2);
        final Node n3 = new NodeImpl(3);
        final Node n4 = new NodeImpl(4);
        n1.connect(n2);
        n2.connect(n3);
        n3.connect(n4);
        final Message message = new Message("foo", 1, 4);
        n1.send(message);
        assertThat(message.isDelivered(), is(true));
        assertThat(n1.getInbox().getMessages().size(), is(0));
        assertThat(n2.getInbox().getMessages().size(), is(0));
        assertThat(n3.getInbox().getMessages().size(), is(0));
        final List<Message> messages = n4.getInbox().getMessages();
        assertThat(messages.size(), is(1));
        assertThat(messages.get(0), is(message));
    }

    @Test(expected = NullPointerException.class)
    public void sendNullMessage() {
        final Node sut = new NodeImpl(23);
        sut.send(null);
    }

    @Test
    public void sendAlreadyDeliveredMessage() {
        final Node n1 = new NodeImpl(1);
        final Node n2 = new NodeImpl(2);
        n1.connect(n2);
        final Message message = new Message("foo", 1, 2);
        message.setDelivered();
        assertThat(message.isDelivered(), is(true));
        n1.send(message);
        assertThat(n1.getInbox().getMessages().size(), is(0));
        assertThat(n2.getInbox().getMessages().size(), is(0));
    }

    @Test
    public void compareTo() {
        final Node sut1 = new NodeImpl(23);
        final Node sut2 = new NodeImpl(23);
        final Node sut3 = new NodeImpl(42);

        assertThat(sut1.compareTo(sut1), is(0));
        assertThat(sut1.compareTo(sut2), is(0));
        assertThat(sut2.compareTo(sut1), is(0));
        assertThat(sut2.compareTo(sut2), is(0));

        assertThat(sut3.compareTo(sut1), is(1));
        assertThat(sut1.compareTo(sut3), is(-1));
    }
}
