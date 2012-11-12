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
package de.weltraumschaf.neuron.shell;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.weltraumschaf.neuron.Node;
import de.weltraumschaf.neuron.NodeFactory;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Environment {

    private final Map<Integer, Node> nodes = Maps.newHashMap();
    private NodeFactory factory = new NodeFactory();

    Node add() {
        final Node n = factory.newNode();
        add(n);
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
            if (node.hasNeighbor(n)) {
                node.disconnect(n);
            }
        }

        nodes.remove(Integer.valueOf(n.getId()));
    }

    void remove(final int id) {
        if (hasNode(id)) {
            remove(getNode(id));
        }
    }

    void reset() {
        nodes.clear();
        factory = new NodeFactory();
    }

    int size() {
        return nodes.size();
    }

    List<Node> getNodes() {
        return Lists.newArrayList(nodes.values());
    }

    boolean hasNode(final int id) {
        return nodes.containsKey(Integer.valueOf(id));
    }

    Node getNode(final int id) {
        return nodes.get(Integer.valueOf(id));
    }

}
