/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.weltraumschaf.neuron;

import java.util.List;

/**
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

    boolean knows(Node n);

    boolean knows(int id);

    int getId();

    void send(Message msg);

    boolean hasNeighbors();
    List<Node> getNeighbors();

}
