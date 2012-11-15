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
import de.weltraumschaf.commons.Version;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;

/**
 * Prints {@link #HELP} into the shell.
 *
 * @author @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class Help extends BaseCommand implements UseVersion {

    /**
     * Help message for shell users.
     */
    private static final String HELP =
          "This is the Neuron Interactive shell version %s.%n%n"
        + "Available commands:%n%n"
        + "  help                           Show all available commands.%n"
        + "  reset                          Reset the whole environment.%n"
        + "  exit                           Exit the interactive shell.%n%n"

        + "  node add [AMOUNT]              Creates one new node or AMOUNT nodes.%n"
        + "  node del ID                    Disconnect and deletes the node with ID.%n"
        + "  node connect ID NEIGHBOR_ID    Connect two nodes.%n"
        + "  node disconnect ID NEIGHBOR_ID    Connect two nodes.%n"
        + "  node list                      List all nodes.%n"
        + "  node info ID                   Print info of a node.%n"
        + "  node listen ID|all             Listening for events of specified node.%n"
        + "  node unlisten ID|all           Stop listening for events of specified node.%n%n"

        + "  message FROM_ID TO_ID MESSAGE  Send a message from one node to other.%n";
    /**
     * Program version info.
     */
    private Version version;

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public Help(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        getIo().println(String.format(HELP, version.getVersion()));
    }

    @Override
    public void setVersion(final Version v) {
        version = v;
    }

}
