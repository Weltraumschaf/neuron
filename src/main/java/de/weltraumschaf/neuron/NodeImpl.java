/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.weltraumschaf.neuron;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeImpl implements Node {

    private final Map<Integer, Node> neighbours = Maps.newHashMap();
    private final int id;

    public NodeImpl(final int id) {
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
    public boolean knows(final Node n) {
        return knows(n.getId());
    }

    @Override
    public boolean knows(final int id) {
        return getId() == id
               ? true
               : hasNeighbor(id);
    }

    private boolean hasNeighbor(final int id) {
        return neighbours.containsKey(Integer.valueOf(id));
    }

    @Override
    public String toString() {
        return String.format("Node ID: %d Neigbors: %d", id, neighbours.size());
    }

}
