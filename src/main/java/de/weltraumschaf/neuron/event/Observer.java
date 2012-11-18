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
 * A class can implement the <code>Observer</code> interface when it
 * wants to be informed of changes in {@link Observable observable} objects.
 *
 * This is a copy of java.util.Observer with customizations.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Observer {

    /**
     * This method is called whenever the observed object is changed.
     *
     * An application calls an {@link Observable#notifyObservers()} method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object
     * @param   arg   an argument passed to the{@link Observable#notifyObservers()} method.
     */
    void update(Observable o, Object arg);

}
