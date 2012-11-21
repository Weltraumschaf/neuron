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
import de.weltraumschaf.neuron.event.EventHandler;
import de.weltraumschaf.neuron.shell.Environment;
import de.weltraumschaf.neuron.shell.ShellCommand;
import de.weltraumschaf.neuron.shell.Token;
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

    private ShellCommand createShellCommand(ShellCommand.MainType m) {
        return new ShellCommand(m, args);
    }

    private ShellCommand createShellCommand(ShellCommand.MainType m, ShellCommand.SubType s) {
        return new ShellCommand(m, s, args);
    }

    @Test
    public void newCommand_exit() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.EXIT);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof Exit), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_help() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.HELP);
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
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.NODE, ShellCommand.SubType.ADD);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeAdd), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeConnect() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.NODE, ShellCommand.SubType.CONNECT);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeConnect), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeDisconnect() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.NODE, ShellCommand.SubType.DISCONNECT);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeDisconnect), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeDel() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.NODE, ShellCommand.SubType.DEL);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeDel), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeInfo() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.NODE, ShellCommand.SubType.INFO);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeInfo), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeList() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.NODE, ShellCommand.SubType.LIST);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeList), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeListen() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.NODE, ShellCommand.SubType.LISTEN);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeListen), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_nodeUnlisten() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.NODE, ShellCommand.SubType.UNLISTEN);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof NodeUnlisten), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }

    @Test
    public void newCommand_reset() {
        final ShellCommand shellCmd = createShellCommand(ShellCommand.MainType.RESET);
        final Command cmd = sut.newCommand(shellCmd);
        assertThat((cmd instanceof Reset), is(true));
        assertThat(((BaseCommand) cmd).getIo(), is(io));
        assertThat(((BaseCommand) cmd).getEnv(), is(env));
        assertThat(((BaseCommand) cmd).getArguments(), is(args));
    }
}
