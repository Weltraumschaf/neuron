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

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Vector;

/**
 * This class represents an observable object, or "data" in the model-view paradigm. It can be subclassed to represent
 * an object that the application wants to have observed. <p> An observable object can have one or more observers. An
 * observer may be any object that implements interface <tt>Observer</tt>. After an observable instance changes, an
 * application calling the
 * <code>Observable</code>'s
 * <code>notifyObservers</code> method causes all of its observers to be notified of the change by a call to their
 * <code>update</code> method. <p> The order in which notifications will be delivered is unspecified. The default
 * implementation provided in the Observable class will notify Observers in the order in which they registered interest,
 * but subclasses may change this order, use no guaranteed order, deliver notifications on separate threads, or may
 * guarantee that their subclass follows this order, as they choose. <p> Note that this notification mechanism is has
 * nothing to do with threads and is completely separate from the <tt>wait</tt> and <tt>notify</tt> mechanism of class
 * <tt>Object</tt>. <p> When an observable object is newly created, its set of observers is empty. Two observers are
 * considered the same if and only if the <tt>equals</tt> method returns true for them.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Observable {

    /**
     * Indicates changed state of the observable.
     */
    private boolean changed = false;

    /**
     * List of observers.
     */
    private final List<Observer> obs = Lists.newArrayList();

    /**
     * Construct an Observable with zero Observers.
     */
    public Observable() {
        super();
    }

    /**
     * Adds an observer to the set of observers for this object, provided that it is not the same as some observer
     * already in the set. The order in which notifications will be delivered to multiple observers is not specified.
     * See the class comment.
     *
     * @param o an observer to be added.
     * CHECKSTYLE:OFF
     * @throws NullPointerException if the parameter o is null.
     * CHECKSTYLE:ON
     */
    public void addObserver(final Observer o) {
        if (o == null) {
            throw new NullPointerException("Argument is null!");
        }

        synchronized (obs) {
            if (!obs.contains(o)) {
                obs.add(o);
            }
        }
    }

    /**
     * Deletes an observer from the set of observers of this object. Passing
     * <CODE>null</CODE> to this method will have no effect.
     *
     * @param o the observer to be deleted.
     */
    public void deleteObserver(final Observer o) {
        synchronized (obs) {
            obs.remove(o);
        }
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers and then call the
     * <code>clearChanged</code> method to indicate that this object has no longer changed. <p> Each observer has its
     * <code>update</code> method called with two arguments: this observable object and
     * <code>null</code>. In other words, this method is equivalent to: <blockquote><tt>
     * notifyObservers(null)</tt></blockquote>
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, java.lang.Object)
     */
    public void notifyObservers() {
        notifyObservers(null);
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers and then call the
     * <code>clearChanged</code> method to indicate that this object has no longer changed. <p> Each observer has its
     * <code>update</code> method called with two arguments: this observable object and the
     * <code>arg</code> argument.
     *
     * @param arg any object.
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, java.lang.Object)
     */
    public void notifyObservers(final Object arg) {
        /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
        List<Observer> localCopy;

        synchronized (this) {
            /* We don't want the Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each Observable from
             * the Vector and store the state of the Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added Observer will miss a
             *   notification in progress
             * 2) a recently unregistered Observer will be
             *   wrongly notified when it doesn't care
             */
            if (!changed) {
                return;
            }
            localCopy = Lists.newArrayList(obs);
            clearChanged();
        }

        for (final Observer observer : localCopy) {
            observer.update(this, arg);
        }
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    public void deleteObservers() {
        synchronized (obs) {
            obs.clear();
        }
    }

    /**
     * Marks this <tt>Observable</tt> object as having been changed; the <tt>hasChanged</tt> method will now return
     * <tt>true</tt>.
     */
    protected void setChanged() {
        synchronized (this) {
            changed = true;
        }
    }

    /**
     * Indicates that this object has no longer changed, or that it has already notified all of its observers of its
     * most recent change, so that the <tt>hasChanged</tt> method will now return <tt>false</tt>. This method is called
     * automatically by the
     * <code>notifyObservers</code> methods.
     *
     * @see Observable#notifyObservers()
     * @see Observable#notifyObservers(java.lang.Object)
     */
    protected void clearChanged() {
        synchronized (this) {
            changed = false;
        }
    }

    /**
     * Tests if this object has changed.
     *
     * @return  <code>true</code> if and only if the <code>setChanged</code> method has been called more recently than
     * the <code>clearChanged</code> method on this object; <code>false</code> otherwise.
     * @see Observable#clearChanged()
     * @see Observable#setChanged()
     */
    public boolean hasChanged() {
        synchronized (this) {
            return changed;
        }
    }

    /**
     * Returns the number of observers of this <tt>Observable</tt> object.
     *
     * @return the number of observers of this object.
     */
    public int countObservers() {
        synchronized (obs) {
            return obs.size();
        }
    }

}
