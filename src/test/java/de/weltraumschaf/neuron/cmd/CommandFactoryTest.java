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
import de.weltraumschaf.commons.shell.ShellCommand;
import de.weltraumschaf.commons.shell.Token;
import de.weltraumschaf.neuron.event.EventHandler;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.NeuronMainType;
import de.weltraumschaf.neuron.shell.NeuronSubType;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CommandFactoryTest {

    private final Version version = new Version("/de/weltraumschaf/neuron/version.properties");
    private final IO io = mock(IO.class);
    private final Environment env = spy(new Environment(new EventHandler(io)));
    private final List<Token> args = Collections.emptyList();
    private final CommandFactory sut = new CommandFactory(env, io, version);

    private ShellCommand createShellCommand(NeuronMainType m) {
        return new ShellCommand(m, null, args);
    }

    private ShellCommand createShellCommand(NeuronMainType m, NeuronSubType s) {
        return new ShellCommand(m, s, args);
    }

    @Test
    public void newCommand_exit() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.EXIT);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof Exit), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_help() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.HELP);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof Help), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
        assertThat((cmd instanceof UseVersion), is(true));
        assertThat(((UseVersion) cmd).getVersion(), is(version));
    }

    @Test
    public void newCommand_nodeAdd() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.NODE, NeuronSubType.ADD);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeAdd), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeConnect() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.NODE, NeuronSubType.CONNECT);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeConnect), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeDisconnect() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.NODE, NeuronSubType.DISCONNECT);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeDisconnect), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeDel() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.NODE, NeuronSubType.DEL);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeDel), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeInfo() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.NODE, NeuronSubType.INFO);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeInfo), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeList() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.NODE, NeuronSubType.LIST);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeList), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeListen() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.NODE, NeuronSubType.LISTEN);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeListen), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeUnlisten() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.NODE, NeuronSubType.UNLISTEN);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeUnlisten), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_reset() {
        final ShellCommand shellCmd = createShellCommand(NeuronMainType.RESET);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof Reset), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

}
