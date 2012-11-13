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
package de.weltraumschaf.neuron.cmd;

import de.weltraumschaf.commons.Version;

/**
 * Classes implementing this interface may use a {@link Version}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface UseVersion {

    /**
     * Injection point for version.
     *
     * @param v version t use.
     */
    void setVersion(Version v);

}
