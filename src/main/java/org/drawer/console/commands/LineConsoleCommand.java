package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;
import org.drawer.console.elements.LineElement;

import static java.lang.String.format;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 * @see ConsoleCommand
 */

public final class LineConsoleCommand extends RectangleConsoleCommand {

    private static final String PATTERN = "^[L] [0-9]+ [0-9]+ [0-9]+ [0-9]+$";
    private static final int X1_PARAMETER_INDEX = 1;
    private static final int Y1_PARAMETER_INDEX = 2;
    private static final int X2_PARAMETER_INDEX = 3;
    private static final int Y2_PARAMETER_INDEX = 4;

    private int x1 = 0;

    private int y1 = 0;

    private int x2 = 0;

    private int y2 = 0;

    @Override
    protected String getPattern() {
        return PATTERN;
    }

    @Override
    protected Canvas executeCommand(@NonNull Canvas canvas) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                canvas.getElements()[x][y] = new LineElement();
            }
        }
        return canvas;
    }

    @Override
    protected void validateCommand(@NonNull Canvas canvas) {
        super.validateCommand(canvas);
        var x1 = Integer.parseInt(parameters[X1_PARAMETER_INDEX]);
        var y1 = Integer.parseInt(parameters[Y1_PARAMETER_INDEX]);
        var x2 = Integer.parseInt(parameters[X2_PARAMETER_INDEX]);
        var y2 = Integer.parseInt(parameters[Y2_PARAMETER_INDEX]);
        if (!(x1 == x2) && !(y1 == y2)) {
            throw new CommandValidationException(format("Line should be either horizontal (y1 == y2) " +
                    "or vertical (x1 == x2). Provided values: (%d, %d) (%d, %d)", x1, y1, x2, y2));
        }
        if (x1 < x2) {
            this.x1 = x1;
            this.x2 = x2;
        } else {
            this.x1 = x2;
            this.x2 = x1;
        }
        if (y1 < y2) {
            this.y1 = y1;
            this.y2 = y2;
        } else {
            this.y1 = y2;
            this.y2 = y1;
        }
    }
}
