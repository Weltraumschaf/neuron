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

/**
 * This class represents an observable object.
 *
 * It can be subclassed to represent an object that the application wants to have observed.
 *
 * An observable object can have one or more observers. An observer may be any object that
 * implements interface {@link Observer}. After an observable instance changes, an  application
 * calling the {@link Observable#notifyObservers} method causes all of its observers to be notified
 * of the change by a call to their {@link Observer#update} method.
 *
 * The order in which notifications will be delivered is unspecified. The default implementation
 * provided in the {@link DefaultObservable} class will notify {@link Observers} in the order in
 * which they registered interest, but subclasses may change this order, use no guaranteed order,
 * deliver notifications on separate threads, or may guarantee that their subclass follows this order,
 * as they choose.
 *
 * Note that this notification mechanism is has nothing to do with threads and is completely separate
 * from the {@link Object#wait() and {@link Object#notify()} mechanism of class {@link Object}.
 *
 * When an observable object is newly created, its set of observers is empty. Two observers are
 * considered the same if and only if the {@link Object#equals()} method returns true for them.
 *
 * This is a copy of java.util.Observable with customizations.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class DefaultObservable implements Observable {

    /**
     * Indicates changed state of the observable.
     */
    private boolean changed;

    /**
     * List of observers.
     */
    private final List<Observer> obs = Lists.newArrayList();

    /**
     * Construct an DefaultObservable with zero Observers.
     */
    public DefaultObservable() {
        super();
    }

    @Override
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

    @Override
    public void deleteObserver(final Observer o) {
        synchronized (obs) {
            obs.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }

    @Override
    public void notifyObservers(final Object arg) {
        /*
         * A temporary buffer, used as a snapshot of the state of
         * current Observers.
         */
        List<Observer> localCopy;

        synchronized (this) {
            /* We don't want the Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each DefaultObservable from
             * the List and store the state of the Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added Observer will miss a
             *   notification in progress
             * 2) a recently unregistered Observer will be
             *   wrongly notified when it doesn't care
             */
            if (! changed) {
                return;
            }
            localCopy = Lists.newArrayList(obs);
            clearChanged();
        }

        for (final Observer observer : localCopy) {
            observer.update(this, arg);
        }
    }

    @Override
    public void deleteObservers() {
        synchronized (obs) {
            obs.clear();
        }
    }

    @Override
    public boolean hasChanged() {
        synchronized (this) {
            return changed;
        }
    }

    @Override
    public int countObservers() {
        synchronized (obs) {
            return obs.size();
        }
    }

    /**
     * Marks this <tt>DefaultObservable</tt> object as having been changed; the {@link #hasChanged()}
     * method will now return <tt>true</tt>.
     */
    protected void setChanged() {
        synchronized (this) {
            changed = true;
        }
    }

    /**
     * Indicates that this object has no longer changed, or that it has already notified all of its observers of its
     * most recent change, so that the {@link #hasChanged()} method will now return <tt>false</tt>.
     *
     * This method is called automatically by the {@link #notifyObservers()} methods.
     *
     * @see Observable#notifyObservers()
     * @see Observable#notifyObservers(java.lang.Object)
     */
    protected void clearChanged() {
        synchronized (this) {
            changed = false;
        }
    }

}
