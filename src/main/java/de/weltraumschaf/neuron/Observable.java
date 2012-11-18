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

/**
 * This interface represents an observable object.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Observable {

    /**
     * Adds an observer to the set of observers for this object, provided that it is not the same as some observer
     * already in the set. The order in which notifications will be delivered to multiple observers is not specified.
     *
     * @param o an observer to be added
     * CHECKSTYLE:OFF
     * @throws NullPointerException if, the parameter o is null.
     * CHECKSTYLE:ON
     */
    void addObserver(Observer o);

    /**
     * Returns the number of observers of this <tt>Observable</tt> object.
     *
     * @return the number of observers of this object.
     */
    int countObservers();

    /**
     * Deletes an observer from the set of observers of this object.
     *
     * Passing <code>null</code> to this method will have no effect.
     *
     * @param o the observer to be deleted
     */
    void deleteObserver(Observer o);

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    void deleteObservers();

    /**
     * Tests if this object has changed.
     *
     * @return  <code>true</code> if and only if the {@link #setChanged()} method has been called more recently than
     * the {@link #clearChanged()} method on this object; <code>false</code> otherwise.
     * @see Observable#clearChanged()
     * @see Observable#setChanged()
     */
    boolean hasChanged();

    /**
     * If this object has changed, as indicated by the {@link #hasChanged()} method, then notify all of its observers
     * and then call the {@link #clearChanged()} method to indicate that this object has no longer changed.
     *
     * Each observer has its {@link Observer#update()} method called with two arguments: this observable object and
     * <code>null</code>. In other words, this method is equivalent to: {@link #notifyObservers(null)}.
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, java.lang.Object)
     */
    void notifyObservers();

    /**
     * If this object has changed, as indicated by the {@link #hasChanged()} method, then notify all of its observers
     * and then call the {@link #clearChanged()} method to indicate that this object has no longer changed.
     *
     * Each observer has its {@link Observer#update()} method called with two arguments: this observable object and the
     * <code>arg</code> argument.
     *
     * @param arg any object
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(DefaultObservable, java.lang.Object)
     */
    void notifyObservers(Object arg);

}
