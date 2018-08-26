package org.drawer.console.commands;

import org.drawer.console.elements.Canvas;

/**
 * Class methods should be executed in the following order:
 * <pre>{@code
 *      consoleCommand.isApplicable(command);
 *      consoleCommand.validate(canvas);
 *      consoleCommand.execute(canvas);
 * }</pre>
 * because {@link ConsoleCommand#isApplicable(String)} will store split command parameters,
 * {@link ConsoleCommand#validate(Canvas)} will parse those parameters
 * and {@link ConsoleCommand#execute(Canvas)} will apply command to the canvas.
 *
 * @author Maksym Bondarenko
 * @version 1.0 25.08.18
 */
public interface ConsoleCommand {

    /**
     * Validates provided command against pattern and stores command parameters using space as splitter
     */
    boolean isApplicable(String command);

    /**
     * Applies validated command to the provided canvas
     */
    Canvas execute(Canvas canvas);

    /**
     * Validates stored command parameters.
     *
     * @throws CommandValidationException on command parameters validation issues
     */
    void validate(Canvas canvas) throws CommandValidationException;
}
