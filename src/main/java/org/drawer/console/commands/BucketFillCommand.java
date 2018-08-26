package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;
import org.drawer.console.elements.ColouredElement;

import static java.lang.String.format;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 * @see ConsoleCommand
 */

public final class BucketFillCommand extends AbstractCommand {

    private static final String PATTERN = "^[B] [0-9]+ [0-9]+ [a-zA-Z0-9{1}]$";
    private static final int X_PARAMETER_INDEX = 1;
    private static final int Y_PARAMETER_INDEX = 2;
    private static final int COLOUR_PARAMETER_INDEX = 3;

    private int x = 0;

    private int y = 0;

    @Override
    protected String getPattern() {
        return PATTERN;
    }

    @Override
    protected Canvas executeCommand(@NonNull Canvas canvas) {
        var colour = parameters[COLOUR_PARAMETER_INDEX].charAt(0);
        return fillArea(x, y, colour, canvas);
    }

    @Override
    protected void validateCommand(Canvas canvas) {
        var x = Integer.parseInt(parameters[X_PARAMETER_INDEX]);
        var y = Integer.parseInt(parameters[Y_PARAMETER_INDEX]);

        if (x < 1 || x > canvas.getWidth()) {
            throw new CommandValidationException(format("x value [%d] should be greater than 0 " +
                    "and less than canvas width [%d]", x, canvas.getWidth()));
        }
        if (y < 1 || y > canvas.getHeight()) {
            throw new CommandValidationException(format("y value [%d] should be greater than 0 " +
                    "and less than canvas height [%d]", y, canvas.getHeight()));
        }

        this.x = x;
        this.y = y;
    }

    /**
     * 'Stack' (simulated with array) based flood fill algorithm. Might require more memory due to 'stack' size
     * {@code maxX*maxY*2}, but doesn't use recursion.
     */
    private Canvas fillArea(int x, int y, char fill, Canvas canvas) {
        int maxX = canvas.getWidth() - 1;
        int maxY = canvas.getHeight() - 1;
        int[][] stack = new int[(maxX + 1) * (maxY + 1)][2];
        int index = 0;

        stack[0][0] = x;
        stack[0][1] = y;
        var elements = canvas.getElements();
        char original = elements[x][y].getRepresentation();
        elements[x][y] = new ColouredElement(fill);

        while (index >= 0) {
            x = stack[index][0];
            y = stack[index][1];
            index--;

            if ((x > 0) && (elements[x - 1][y].getRepresentation() == original)) {
                elements[x - 1][y] = new ColouredElement(fill);
                index++;
                stack[index][0] = x - 1;
                stack[index][1] = y;
            }

            if ((x < maxX) && (elements[x + 1][y].getRepresentation() == original)) {
                elements[x + 1][y] = new ColouredElement(fill);
                index++;
                stack[index][0] = x + 1;
                stack[index][1] = y;
            }

            if ((y > 0) && (elements[x][y - 1].getRepresentation() == original)) {
                elements[x][y - 1] = new ColouredElement(fill);
                index++;
                stack[index][0] = x;
                stack[index][1] = y - 1;
            }

            if ((y < maxY) && (elements[x][y + 1].getRepresentation() == original)) {
                elements[x][y + 1] = new ColouredElement(fill);
                index++;
                stack[index][0] = x;
                stack[index][1] = y + 1;
            }
        }
        return canvas;
    }
}
