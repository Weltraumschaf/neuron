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

import de.weltraumschaf.commons.IO;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;

/**
 *
 * @author @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class ExitCommand extends BaseCommand {

    public ExitCommand(final Environment env, final IO io) {
        this(env, io, DEFAULT_ARGUMETS);
    }

    public ExitCommand(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        getIo().println("bye bye!");
    }


}
