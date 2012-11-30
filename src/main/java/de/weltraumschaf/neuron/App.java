package de.weltraumschaf.neuron;

import de.weltraumschaf.commons.IOStreams;
import de.weltraumschaf.commons.InvokableAdapter;
import de.weltraumschaf.commons.Version;
import de.weltraumschaf.neuron.shell.InteractiveShell;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main neuron application.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class App extends InvokableAdapter {

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
        boolean debug = false;

        try {
            if (args.length == 1 && "-d".equals(args[0])) {
                debug = true;
            }
            InvokableAdapter.main(app, IOStreams.newDefault(), debug);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
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
        final Version version = new Version("/de/weltraumschaf/neuron/version.properties");
        version.load();
        final InteractiveShell shell = new InteractiveShell(getIoStreams(), version);
        shell.start();
    }

}
