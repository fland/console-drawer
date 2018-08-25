package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;
import org.drawer.console.elements.LineElement;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

public class LineConsoleCommand implements ConsoleCommand {

    private static final String PATTERN = "^[L] [0-9]+ [0-9]+ [0-9]+ [0-9]+$";

    @Override
    public boolean isApplicable(@NonNull String command) {
        return command.matches(PATTERN);
    }

    @Override
    public Canvas execute(@NonNull String command, @NonNull Canvas canvas) {
        var parameters = command.split(" ");
        var x1 = Integer.parseInt(parameters[1]);
        var y1 = Integer.parseInt(parameters[2]);
        var x2 = Integer.parseInt(parameters[3]);
        var y2 = Integer.parseInt(parameters[4]);
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                canvas.getElements()[x][y] = new LineElement();
            }
        }
        return canvas;
    }
}
