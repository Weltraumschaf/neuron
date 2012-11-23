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
 * Used to send messages from one node to another identified bey their ids.
 *
 * A message is immutable.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Message {

    /**
     * The message string.
     */
    private final String msg;

    /**
     * The originator node id.
     */
    private final int from;

    /**
     * The receiver node id.
     */
    private final int to;

    /**
     * Indicates if the message was delivered to it's receiver.
     */
    private boolean delivered;

    /**
     * Dedicated constructor.
     *
     * @param msg the message string
     * @param from originators id
     * @param to receivers id
     */
    public Message(final String msg, final int from, final int to) {
        super();
        this.msg  = msg;
        this.from = from;
        this.to   = to;
    }

    /**
     * Getter for message text.
     *
     * @return message string
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Getter for originators id.
     *
     * @return positive integer id
     */
    public int getFrom() {
        return from;
    }


    /**
     * Getter for receivers id.
     *
     * @return positive integer id
     */
    public int getTo() {
        return to;
    }

    /**
     * Mark the message as delivered to it's receiver.
     */
    public void setDelivered() {
        delivered = true;
    }

    /**
     * Whether the message was delivered to it's receiver or not.
     *
     * @return return true if delivered, else false
     */
    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public String toString() {
        return String.format("%d -> %d: '%s'", getFrom(), getTo(), getMsg());
    }

}
