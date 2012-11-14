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

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.weltraumschaf.neuron.Message;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link Node}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeImpl implements Node {

    /**
     * Holds all known neighbors.
     */
    private final Map<Integer, Node> neighbours = Maps.newHashMap();

    /**
     * System wide unique identity.
     */
    private final int id;

    /**
     * Default constructor.
     *
     * @param id system wide unique identity
     */
    public NodeImpl(final int id) {
        super();
        this.id = id;
    }

    @Override
    public void connect(final Node n) {
        if (hasNeighbor(n.getId())) {
            throw new IllegalArgumentException(String.format("Already connected to node with address %d!", n.getId()));
        }

        neighbours.put(Integer.valueOf(n.getId()), n);
    }

    @Override
    public void disconnect(final Node n) {
        if (!hasNeighbor(n.getId())) {
            throw new IllegalArgumentException(String.format("Not connected to node with address %d!", n.getId()));
        }

        neighbours.remove(Integer.valueOf(n.getId()));
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void send(final Message msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (! (obj instanceof Node)) {
            return false;
        }

        final Node other = (Node) obj;
        return id == other.getId();
    }

    @Override
    public boolean hasNeighbor(final Node neighbor) {
        return hasNeighbor(neighbor.getId());
    }

    @Override
    public boolean hasNeighbor(final int neighborsId) {
        return neighbours.containsKey(Integer.valueOf(neighborsId));
    }

    @Override
    public String toString() {
        return String.format("Node ID: %d Neigbors: %d", id, neighbours.size());
    }

    @Override
    public boolean hasNeighbors() {
        return ! neighbours.isEmpty();
    }

    @Override
    public List<Node> getNeighbors() {
        return Lists.newArrayList(neighbours.values());
    }

}
