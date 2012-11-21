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
import de.weltraumschaf.neuron.node.Node;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.Token;
import java.util.List;
import java.util.Random;

/**
 * Executes `sample tree [DEPTH] [CHILDS]` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SampleTree extends BaseCommand {

    /**
     * Random number generator.
     */
    private static final Random RANDOM = new Random();

    /**
     * Random max number.
     */
    private static final int MAX_RANDOM_DEPTH = 6;

    /**
     * Default for children count.
     */
    private static final int DEFUALT_CHILDREN_COUNT = 2;

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public SampleTree(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {
        int depth = 0;
        int childrenCount = DEFUALT_CHILDREN_COUNT;

        if (getArguments().isEmpty()) {
            depth = RANDOM.nextInt(MAX_RANDOM_DEPTH) + 1; // Generate numbers from 1..7.
        } else if (getArguments().size() == 1) {
            final Token<Integer> arg1 = getArguments().get(0);
            depth = arg1.getValue();
        } else if (getArguments().size() == 2) {
            final Token<Integer> arg1 = getArguments().get(0);
            depth = arg1.getValue();
            final Token<Integer> arg2 = getArguments().get(1);
            childrenCount = arg2.getValue();
        }

        createTree(depth, childrenCount);
        getIo().println(String.format("Generated tree with depth '%d' and children count '%d'.", depth, childrenCount));
    }

    /**
     * Creates tree recursive.
     *
     * @param depth tree depth
     * @param childrenCount child count ofeach node
     * @return return a sub tree
     */
    private Node createTree(final int depth, final int childrenCount) {
        if (1 == depth) {
            return getEnv().add();
        }

        final Node n = getEnv().add();

        for (int i = 0; i < childrenCount; ++i) {
            n.connect(createTree(depth - 1, childrenCount));
        }

        return n;
    }

}
