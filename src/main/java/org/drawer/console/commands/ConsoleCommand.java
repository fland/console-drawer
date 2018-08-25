package org.drawer.console.commands;

import org.drawer.console.elements.Canvas;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

public interface ConsoleCommand {

    boolean isApplicable(String command);

    Canvas execute(String command, Canvas canvas);
}
