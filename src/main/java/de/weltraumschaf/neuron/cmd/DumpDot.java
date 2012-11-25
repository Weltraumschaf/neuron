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
import de.weltraumschaf.neuron.DotGenerator;
import de.weltraumschaf.neuron.shell.Environment;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Executes `dump dot FILE` command.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class DumpDot extends BaseCommand {

    /**
     * Dot language generator.
     */
    private final DotGenerator dotGenerator = new DotGenerator(getEnv());

    /**
     * Dedicated constructor.
     *
     * @param env shell environment
     * @param io shell I/O
     * @param arguments command arguments
     */
    public DumpDot(final Environment env, final IO io, final List<Token> arguments) {
        super(env, io, arguments);
    }

    @Override
    public void execute() {

        if (getArguments().size() == 1) {
            final Token<String> fileArg = getArguments().get(0);
            // FIXME Use basename w/o file extension as name.
            dotGenerator.setName(fileArg.getValue());
        }

        // FIXME allways stores in CWD
        final File file = new File(dotGenerator.getFileName());

        try {
            FileUtils.writeStringToFile(file, dotGenerator.toString());
            getIo().println(String.format("Dumped nodes to dot file in '%s'.", file.getAbsoluteFile()));
        } catch (IOException ex) {
            getIo().println(String.format("Can't write dot file '%s'! Reason: %s.",
                                          file.getAbsoluteFile(), ex.getMessage()));
        }
    }

}
