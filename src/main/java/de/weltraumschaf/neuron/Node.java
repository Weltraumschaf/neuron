/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.weltraumschaf.neuron;

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

    int getId();

    void send(Message msg);

}
