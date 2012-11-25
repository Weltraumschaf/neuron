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
import de.weltraumschaf.commons.shell.Token;
import de.weltraumschaf.neuron.node.Message;
import de.weltraumschaf.neuron.node.MessageBox;
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.shell.Environment;
import java.util.List;

/**
 * Executes `node info ID` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
class NodeInfo extends BaseCommand {

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public NodeInfo(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        final Token<Integer> nodeId = getArguments().get(0);

        if (getEnv().hasNode(nodeId.getValue())) {
            final Node inspectedNode = getEnv().getNode(nodeId.getValue());
            final StringBuilder info = new StringBuilder();
            info.append(String.format("%s%n", inspectedNode));
            infosAboutNeighbors(info, inspectedNode);
            info.append(String.format("%n"));
            infosAboutReceivedMessages(info, inspectedNode);
            getIo().println(info.toString());
        } else {
            getIo().println(String.format("Node with id %d does not exist!", nodeId.getValue()));
        }
    }

    /**
     * Append summary about neighbors to info buffer.
     *
     * @param info buffers info string
     * @param inspectedNode node to get information from
     */
    private void infosAboutNeighbors(final StringBuilder info, final Node inspectedNode) {
        info.append(String.format("Neighbors:%n"));

        if (inspectedNode.hasNeighbors()) {
            for (final Node neighbor : inspectedNode.getNeighbors()) {
                info.append(String.format("  %s%n", neighbor));
            }
        } else {
            info.append(String.format("  Has no neighbors!%n"));
        }
    }

    /**
     * Append summary about received messages to info buffer.
     *
     * @param info buffers info string
     * @param inspectedNode node to get information from
     */
    private void infosAboutReceivedMessages(final StringBuilder info, final Node inspectedNode) {
        info.append(String.format("Received messages:%n"));
        final MessageBox inbox = inspectedNode.getInbox();

        if (inbox.isEmpty()) {
            info.append(String.format("  Has no messages received!%n"));
        } else {
            int i = 1;
            for (final Message msg : inbox.getMessages()) {
                info.append(String.format("  %d. %s%n", i, msg));
                ++i;
            }
        }
    }

}
