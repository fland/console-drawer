package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;
import org.drawer.console.elements.ColouredElement;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 * @see ConsoleCommand
 */

public final class BucketFillCommand extends AbstractCommand {

    private static final String PATTERN = "^[B] [0-9]+ [0-9]+ [a-zA-Z0-9{1}]$";

    @Override
    protected String getPattern() {
        return PATTERN;
    }

    @Override
    public Canvas execute(@NonNull Canvas canvas) {
        var x = Integer.parseInt(parameters[1]);
        var y = Integer.parseInt(parameters[2]);
        var colour = parameters[3].charAt(0);
        return fillArea(x, y, colour, canvas);
    }

    @Override
    public void validate(Canvas canvas) {

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
