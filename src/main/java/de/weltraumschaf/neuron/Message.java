/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.weltraumschaf.neuron;

/**
 *
 * @author sxs
 */
public class Message {

    private final String msg;
    private final int from;
    private final int to;

    public Message(final String msg, final int from, final int to) {
        this.msg = msg;
        this.from = from;
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
    
}
