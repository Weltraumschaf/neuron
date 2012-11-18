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
package de.weltraumschaf.neuron.event;

/**
 * Events emitted by {@link Observable}.
 *
 * @author "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
public class Event {

    /**
     * Type of event.
     */
    public enum Type {
        /** Events occurring by dis/conecting nodes. */
        CONNECTION,
        /** Events occurring by send/receive messages between nodes. */
        MESSAGING;
    }

    /**
     * Type of event.
     */
    private final Type type;

    /**
     * Description of event.
     */
    private final String description;

    /**
     * Source of the event.
     */
    private final Observable source;

    /**
     * Dedicated constructor.
     *
     * @param type type of event
     * @param description description of event
     * @param source of the event
     */
    public Event(final Type type, final String description, final Observable source) {
        super();
        this.type = type;
        this.description = description;
        this.source = source;
    }

    /**
     * Getter for type.
     *
     * @return type of event, never null
     */
    public Type getType() {
        return type;
    }

    /**
     * Getter for description.
     *
     * @return  description of event, never null
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the source of the event.
     *
     * @return observable which have emitted the event
     */
    public Observable getSource() {
        return source;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (from %s)", getType(), getDescription(), getSource());
    }

}
