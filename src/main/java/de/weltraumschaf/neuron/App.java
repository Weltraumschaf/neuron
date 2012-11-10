package de.weltraumschaf.neuron;

import de.weltraumschaf.commons.IOStreams;
import de.weltraumschaf.commons.InvokableAdapter;

/**
 *
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

        registerShutdownHook(new Runnable() {

            public void run() {
                shell.exit();
            }
        });
        
        shell.start();
    }

}
