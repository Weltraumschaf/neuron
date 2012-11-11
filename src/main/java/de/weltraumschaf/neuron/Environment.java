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
package de.weltraumschaf.neuron;

import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Environment {

    private final Map<Integer, Node> nodes = Maps.newHashMap();
    private int nextId = 0;

    Node add() {
        final Node n = new NodeImpl(getNextId());
        add(n);
        ++nextId;
        return n;
    }

    void add(final Node n) {
        if (nodes.containsValue(n)) {
            throw new IllegalArgumentException(String.format("Node with id %d already added!", n.getId()));
        }

        nodes.put(Integer.valueOf(n.getId()), n);
    }

    /**
     * Disconnect from all nodes and remove from environment nodes.
     *
     * @param n
     */
    void remove(final Node n) {
        if (!nodes.containsValue(n)) {
            return;
        }

        for (final Integer key : nodes.keySet()) {
            final Node node = nodes.get(key);
            node.disconnect(n);
        }

        nodes.remove(Integer.valueOf(n.getId()));
    }

    public void reset() {
        nodes.clear();
        nextId = 0;
    }

    int size() {
        return nodes.size();
    }

    int getNextId() {
        return nextId;
    }

}
