package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 * @see ConsoleCommand
 */

public final class QuitConsoleCommand implements ConsoleCommand {

    private final static String PATTERN = "^[Q]$";

    @Override
    public boolean isApplicable(@NonNull String command) {
        return command.matches(PATTERN);
    }

    @Override
    public Canvas execute(@NonNull Canvas canvas) {
        System.exit(0);
        return canvas;
    }

    @Override
    public void validate(@NonNull Canvas canvas) {

    }
}
