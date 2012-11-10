package de.weltraumschaf.neuron;

import de.weltraumschaf.commons.IOStreams;
import java.io.IOException;

/**
 *
 */
public class App {

    public static void main(final  String[] args ) throws IOException {
        final InteractiveShell shell = new InteractiveShell(IOStreams.newDefault());
        shell.start();
    }
}
