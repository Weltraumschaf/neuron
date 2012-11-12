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

import java.util.List;

/**
 * This type defines a node.
 *
 * Nodes may be connected to each other and communicate to each other.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Node {

    /**
     * Connects this node to an other node.
     *
     * @param n The new neighbor.
     */
    void connect(Node n);

    /**
     * Disconnects this node.
     *
     * @param n Not longer a neighbor.
     */
    void disconnect(Node n);

    /**
     * Determines if this node ha an other node as neighbor.
     *
     * To be neighbor means the node was connected to this node.
     *
     * @param n node to ask for.
     * @return true if n is neighbor else false
     */
    boolean hasNeighbor(Node n);

    /**
     * Determines if this node ha an other node with id as neighbor.
     *
     * @param id id of node to ask for
     * @return true if this node has a neighbor with given id else false
     */
    boolean hasNeighbor(int id);

    /**
     * Get the nodes id.
     *
     * @return value in range [ 0 .. Integer.MAX_VALUE ]
     */
    int getId();

    /**
     * Send a message via this node.
     *
     * @param msg Message to send.
     */
    void send(Message msg);

    /**
     * Ask if this node has neighbors.
     *
     * @return return true if other nodes were connected to this one else false
     */
    boolean hasNeighbors();

    /**
     * Get list of connected nodes.
     *
     * @return never return null, but may return empty list
     */
    List<Node> getNeighbors();

}
