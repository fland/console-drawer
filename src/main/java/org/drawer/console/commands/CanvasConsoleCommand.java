package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */
public class CanvasConsoleCommand implements ConsoleCommand {

    private static final String PATTERN = "^[C] [0-9]+ [0-9]+$";

    @Override
    public boolean isApplicable(@NonNull String command) {
        return command.matches(PATTERN);
    }

    @Override
    public Canvas execute(@NonNull String command, @NonNull Canvas canvas) {
        var parameters = command.split(" ");
        var width = Integer.parseInt(parameters[1]) + 2;
        var length = Integer.parseInt(parameters[2]) + 2;
        canvas = new Canvas(width, length);
        return canvas;
    }
}
