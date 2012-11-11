/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.weltraumschaf.neuron;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.List;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeImpl implements Node {

    private final List<Node> neighbours = Lists.newArrayList();
    private final int id;

    public NodeImpl(final int id) {
        this.id = id;
    }

    public void connect(final Node n) {
        neighbours.add(n);
    }

    public void disconnect(Node n) {
        neighbours.remove(n);
    }

    public int getId() {
        return id;
    }

    public void send(Message msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
