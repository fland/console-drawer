package org.drawer.console.commands;

import org.drawer.console.elements.Canvas;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * @author Maksym Bondarenko
 * @version 1.0 26.08.18
 */
//for some reason I've not been able to make ExpectedSystemExit rule work in spock
public class QuitConsoleCommandJunitTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExit() {
        exit.expectSystemExitWithStatus(0);
        var command = new QuitConsoleCommand();
        command.execute(new Canvas(1, 1, 1));
    }
}
