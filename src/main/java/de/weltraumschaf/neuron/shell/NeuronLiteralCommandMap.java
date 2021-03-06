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

package de.weltraumschaf.neuron.shell;

import de.weltraumschaf.commons.shell.LiteralCommandMap;
import de.weltraumschaf.commons.shell.MainCommandType;
import de.weltraumschaf.commons.shell.SubCommandType;
import java.util.Map;

/**
 * Maps literal strings to keywords.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NeuronLiteralCommandMap extends LiteralCommandMap {

    /**
     * Default constructor.
     */
    public NeuronLiteralCommandMap() {
        super(NeuronSubType.NONE);
    }

    @Override
    protected void initCommandMap(Map<String, MainCommandType> map) {
        for (final NeuronMainType t : NeuronMainType.values()) {
            map.put(t.toString(), t);
        }
    }

    @Override
    protected void initSubCommandMap(Map<String, SubCommandType> map) {
        for (final NeuronSubType t : NeuronSubType.values()) {
            if (t.toString().isEmpty()) {
                continue; // Ignore to do not recognize empty strings as sub command.
            }
            map.put(t.toString(), t);
        }
    }

}
