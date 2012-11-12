package de.weltraumschaf.neuron;

import de.weltraumschaf.commons.InvokableAdapter;
import de.weltraumschaf.neuron.shell.InteractiveShell;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class App extends InvokableAdapter {

    public App(final String[] args) {
        super(args);
    }

    public static void main(final String[] args) {
        final App app = new App(args);

        try {
            InvokableAdapter.main(app);
            app.exit(0);
        } catch (Exception ex) {
            app.getIoStreams().errorln(ex.getMessage());
            app.exit(-1);
        }
    }

    @Override
    public void execute() throws Exception {
        final InteractiveShell shell = new InteractiveShell(getIoStreams());
        shell.start();
    }

}
