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
import de.weltraumschaf.neuron.event.Observer;
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.node.NodeFactory;
import java.util.List;
import java.util.Map;

/**
 * Environment for interactive shell.
 *
 * Holds all created nodes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Environment {

    /**
     * Maps nodes with their id for fast lookup by their id.
     */
    private final Map<Integer, Node> nodes = Maps.newHashMap();

    /**
     * Handle events of {@link Node nodes} it is registered for.
     */
    private final Observer handler;

    /**
     * Factory to create nodes.
     */
    private NodeFactory factory = new NodeFactory();

    /**
     * Dedicated constructor.
     *
     * @param eventHandler handles node events
     */
    public Environment(final Observer eventHandler) {
        super();
        handler = eventHandler;
    }

    /**
     * Create and add a new node to the environment.
     *
     * @return the new created node
     * // CHECKSTYLE:OFF
     * @throws IllegalArgumentException if, same node already added
     * // CHECKSTYLE:ON
     */
    public Node add() {
        return add(factory.newNode());
    }

    /**
     * Add given node to environment.
     *
     * @param n node to add
     * @return the some node passed in as parameter
     * // CHECKSTYLE:OFF
     * @throws IllegalArgumentException if, same node already added
     * // CHECKSTYLE:ON
     */
    public Node add(final Node n) {
        if (nodes.containsValue(n)) {
            throw new IllegalArgumentException(String.format("Node with id %d already added!", n.getId()));
        }

        nodes.put(Integer.valueOf(n.getId()), n);
        return n;
    }

    /**
     * Disconnect from all nodes and remove from environment nodes.
     *
     * @param n node to remove
     * @return removed node
     * CHECKSTYLE:OFF
     * @throws IllegalArgumentException if, node does not exist
     * CHECKSTYLE:ON
     */
    Node remove(final Node n) {
        if (!nodes.containsValue(n)) {
            throw new IllegalArgumentException("Does not have node " + n.toString());
        }

        for (final Integer key : nodes.keySet()) {
            final Node node = nodes.get(key);
            if (node.hasNeighbor(n)) {
                node.disconnect(n);
            }
        }

        return nodes.remove(Integer.valueOf(n.getId()));
    }

    /**
     * Disconnect from all nodes and remove from environment nodes.
     *
     * @param id id of node to remove
     * @return removed node
     * CHECKSTYLE:OFF
     * @throws IllegalArgumentException if, node does not exist
     * CHECKSTYLE:ON
     */
    public Node remove(final int id) {
        return remove(getNode(id));
    }

    /**
     * Reset the environment.
     *
     * - remove all created nodes
     * - set new node factory to reset auto generated node ids
     */
    public void reset() {
        nodes.clear();
        factory = new NodeFactory();
    }

    /**
     * Counts the created nodes.
     *
     * @return amount of created nodes.
     */
    int size() {
        return nodes.size();
    }

    /**
     * Get list of created nodes.
     *
     * @return returns a defense copy
     */
    public List<Node> getNodes() {
        return Lists.newArrayList(nodes.values());
    }

    /**
     * Whether the environment has a node with given id or not.
     *
     * @param id to look for
     * @return true if node exists else false
     */
    public boolean hasNode(final int id) {
        return nodes.containsKey(Integer.valueOf(id));
    }

    /**
     * Get a node for given id.
     *
     * @param id to look for
     * @return node if found
     * // CHECKSTYLE:OFF
     * @throws IllegalArgumentException if, {@link #hasNode(int)} return false
     * // CHECKSTYLE:ON
     */
    public Node getNode(final int id) {
        if (! hasNode(id)) {
            throw new IllegalArgumentException(String.format("Does not have node with id '%d'!", id));
        }
        return nodes.get(Integer.valueOf(id));
    }

    /**
     * Getter for the event handler.
     *
     * @return event handler
     */
    public Observer getHandler() {
        return handler;
    }

}
