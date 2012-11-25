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
import de.weltraumschaf.commons.shell.Token;
import de.weltraumschaf.neuron.shell.Environment;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
public class HelpTest {

    @Test
    public void execute() {
        final Version version = new Version("/de/weltraumschaf/neuron/version.properties");
        final List<Token> args = Collections.emptyList();
        final IO io = mock(IO.class);
        final Help sut = new Help(mock(Environment.class), io, args);

        sut.setVersion(version);
        sut.execute();
        final String expectedHelpMessage = String.format(
            "This is the Neuron Interactive shell version n/a.%n%n"
            + "Available commands:%n%n"
            + "  help                             Show all available commands.%n"
            + "  reset                            Reset the whole environment.%n"
            + "  exit                             Exit the interactive shell.%n%n"
            + "  node add [AMOUNT]                Creates one new node or AMOUNT nodes.%n"
            + "  node del ID                      Disconnect and deletes the node with ID.%n"
            + "  node connect ID NEIGHBOR_ID      Connect two nodes.%n"
            + "  node disconnect ID NEIGHBOR_ID   Connect two nodes.%n"
            + "  node list                        List all nodes.%n"
            + "  node info ID                     Print info of a node.%n"
            + "  node listen ID|all               Listening for events of specified node.%n"
            + "  node unlisten ID|all             Stop listening for events of specified node.%n%n"
            + "  message FROM_ID TO_ID 'MESSAGE'  Send a message from one node to other.%n%n"
            + "  dump dot FILE                    Dump the nodes into a graphviz file.%n%n"
            + "  sample tree [DEPTH] [CHILDREN]   Generates a tree of nodes. If CHILDREN is omitted a binary tree is%n"
            + "                                   generated. If DEPTH is omitted a random number (1..7) is used.%n%n");
        verify(io, times(1)).println(expectedHelpMessage);
    }

}
