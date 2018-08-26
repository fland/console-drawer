package org.drawer.console.commands;

import lombok.NonNull;
import org.drawer.console.elements.Canvas;

/**
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 * @see ConsoleCommand
 */
public final class CanvasConsoleCommand extends AbstractCommand {

    private static final String PATTERN = "^[C] [0-9]+ [0-9]+$";
    private final static int WIDTH_PARAMETER_INDEX = 1;
    private final static int HEIGHT_PARAMETER_INDEX = 2;
    private static final int BORDER_ELEMENTS_SIZE = 2;

    private int width = 0;

    private int height = 0;

    @Override
    protected String getPattern() {
        return PATTERN;
    }

    @Override
    public Canvas execute(@NonNull Canvas canvas) {
        canvas = new Canvas(width, height);
        return canvas;
    }

    @Override
    public void validate(@NonNull Canvas canvas) {
        var width = Integer.parseInt(parameters[WIDTH_PARAMETER_INDEX]);
        if (width < 1) {
            throw new CommandValidationException("Canvas width should be greater than 0");
        }
        var height = Integer.parseInt(parameters[HEIGHT_PARAMETER_INDEX]);
        if (height < 1) {
            throw new CommandValidationException("Canvas height should be greater than 0");
        }
        this.width = width + BORDER_ELEMENTS_SIZE;
        this.height = height + BORDER_ELEMENTS_SIZE;
    }
}
