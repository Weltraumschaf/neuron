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

/**
 * Create nodes with distinct id.
 *
 * The used ids wil start at 0 up to {@link Integer#MAX_VALUE}. If you want to restart creating nodes
 * with ids from 0 you must use a new Factory instance.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class NodeFactory {

    /**
     * Next id to use.
     */
    private int nextId = -1;

    /**
     * Increments {@link #nextId} and returns it.
     *
     * @return distinct value from 0 to {@link Integer#MAX_VALUE}
     */
    private int getNextId() {
        ++nextId;
        return nextId;
    }

    /**
     * Create node with {@link #nextId} as id.
     *
     * @return new instance
     */
    public Node newNode() {
        return newNode(getNextId());
    }

    /**
     * Creates node with given id.
     *
     * @param id nodes id
     * @return new instance
     */
    public Node newNode(final int id) {
        return new NodeImpl(id);
    }

}
