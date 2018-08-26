package org.drawer.console.elements;

import lombok.Getter;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */
@Getter
public class Canvas {

    private final Element[][] elements;
    private final int width;
    private final int height;
    private final int borderSize;

    public Canvas(int width, int height, int borderSize) {
        this.width = width;
        this.height = height;
        this.borderSize = borderSize;
        elements = new Element[width][height];
        for (int x = 0; x < width; x++) {
            elements[x][0] = new CanvasHorizontalBorder();
            elements[x][height - 1] = new CanvasHorizontalBorder();
        }
        for (int y = 1; y < height - 1; y++) {
            elements[0][y] = new CanvasVerticalBorder();
            elements[width - 1][y] = new CanvasVerticalBorder();
        }
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                elements[x][y] = new EmptyElement();
            }
        }
    }

    public String toString() {
        var builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                builder.append(elements[x][y].getRepresentation());
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
