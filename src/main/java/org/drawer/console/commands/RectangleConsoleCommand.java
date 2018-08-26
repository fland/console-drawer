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

public final class RectangleConsoleCommand extends AbstractCommand {

    private static final String PATTERN = "^[R] [0-9]+ [0-9]+ [0-9]+ [0-9]+$";

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
    public Canvas execute(@NonNull Canvas canvas) {
        for (int x = x1; x <= x2; x++) {
            canvas.getElements()[x][y1] = new LineElement();
            canvas.getElements()[x][y2] = new LineElement();
        }
        for (int y = y1; y <= y2; y++) {
            canvas.getElements()[x1][y] = new LineElement();
            canvas.getElements()[x2][y] = new LineElement();
        }
        return canvas;
    }

    @Override
    public void validate(@NonNull Canvas canvas) {
        var x1 = Integer.parseInt(parameters[X1_PARAMETER_INDEX]);
        if (x1 < 1 || x1 > canvas.getWidth()) {
            throw new CommandValidationException(format("x1 value [%d] should be greater than 0 " +
                    "and less than canvas width [%d]", x1, canvas.getWidth()));
        }
        var y1 = Integer.parseInt(parameters[Y1_PARAMETER_INDEX]);
        if (y1 < 1 || y1 > canvas.getHeight()) {
            throw new CommandValidationException(format("y1 value [%d] should be greater than 0 " +
                    "and less than canvas height [%d]", y1, canvas.getHeight()));
        }
        var x2 = Integer.parseInt(parameters[X2_PARAMETER_INDEX]);
        if (x2 < 1 || x2 > canvas.getWidth()) {
            throw new CommandValidationException(format("x2 value [%d] should be greater than 0 " +
                    "and less than canvas width [%d]", x2, canvas.getWidth()));
        }
        var y2 = Integer.parseInt(parameters[Y2_PARAMETER_INDEX]);
        if (y2 < 1 || y2 > canvas.getWidth()) {
            throw new CommandValidationException(format("y2 value [%d] should be greater than 0 " +
                    "and less than canvas height [%d]", y2, canvas.getHeight()));
        }

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}
