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

import com.google.common.collect.Lists;
import java.util.List;

/**
 * Container for {@link Node nodes} to hold received {@link Message messages}.
 *
 * This object is immutable.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class MessageBox {

    /**
     * Set of received messages.
     */
    private final List<Message> messages = Lists.newArrayList();

    /**
     * Stores a message.
     *
     * @param msg message to store
     */
    void store(final Message msg) {
        messages.add(msg);
    }

    /**
     * Whether the box has no messages or not.
     *
     * @return true if the box contains no messages, false if it contains one or more messages
     */
    public boolean isEmpty() {
        return messages.isEmpty();
    }

    /**
     * Get a copy of the set of messages.
     *
     * @return a defense copy
     */
    public List<Message> getMessages() {
        return Lists.newArrayList(messages); // defense copy.
    }

}
