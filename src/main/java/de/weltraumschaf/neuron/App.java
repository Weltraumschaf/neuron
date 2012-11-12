package de.weltraumschaf.neuron;

import de.weltraumschaf.commons.InvokableAdapter;
import de.weltraumschaf.neuron.shell.InteractiveShell;

/**
 * The main neuron application.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class App extends InvokableAdapter {

    /**
     * Hide constructor because it is not intended to create objects of this type
     * outside of {@link App#main(de.weltraumschaf.commons.Invokable)}.
     *
     * @param args command line arguments
     */
    private App(final String[] args) {
        super(args);
    }

    /**
     * Main entry point for JVM.
     *
     * Catches all {@link Exception exception} and print their message to STDERR
     * and exits with -1.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        final App app = new App(args);

        try {
            InvokableAdapter.main(app);
            app.exit(0);
        } catch (Exception ex) {
            app.getIoStreams().errorln("Exception: " + ex.getMessage());
            app.exit(-1);
        }
    }

    /**
     * Starts interactive shell.
     *
     * @throws Exception if, something bad happens at runtime.
     */
    @Override
    public void execute() throws Exception {
        final InteractiveShell shell = new InteractiveShell(getIoStreams());
        shell.start();
    }

}
