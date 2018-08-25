package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;
import org.drawer.console.elements.ColouredElement;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */

public class BucketFillCommand implements ConsoleCommand {

    private static final String PATTERN = "^[B] [0-9]+ [0-9]+ [a-zA-Z0-9{1}]$";

    @Override
    public boolean isApplicable(@NonNull String command) {
        return command.matches(PATTERN);
    }

    @Override
    public Canvas execute(@NonNull String command, @NonNull Canvas canvas) {
        var parameters = command.split(" ");
        var x = Integer.parseInt(parameters[1]);
        var y = Integer.parseInt(parameters[2]);
        var colour = parameters[3].charAt(0);
        return fillArea(x, y, colour, canvas);
    }

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
